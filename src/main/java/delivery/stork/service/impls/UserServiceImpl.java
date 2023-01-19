package delivery.stork.service.impls;

import delivery.stork.exception.EmailAlreadyExist;
import delivery.stork.exception.TokenException;
import delivery.stork.mapper.UserMapper;
import delivery.stork.model.dto.JwtDto;
import delivery.stork.model.dto.UserDto;
import delivery.stork.model.entity.ActivationToken;
import delivery.stork.model.entity.ResetPassword;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.LoginRequest;
import delivery.stork.model.wrapper.RegisterRequest;
import delivery.stork.model.wrapper.ResetPasswordRequest;
import delivery.stork.repository.ActivationTokenRepo;
import delivery.stork.repository.ResetPasswordRepo;
import delivery.stork.repository.UserRepo;
import delivery.stork.secutiry.impls.UserDetailsImpl;
import delivery.stork.service.MailSenderService;
import delivery.stork.service.UserService;
import delivery.stork.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final ActivationTokenRepo activationTokenRepo;
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;
    private final JwtUtil jwtUtil;
    private final ResetPasswordRepo resetPasswordRepo;

    private boolean validateEmail(String email) throws AddressException {
        InternetAddress internetAddress = new InternetAddress(email);
        internetAddress.validate();
        return true;
    }
    private Authentication authenticate(LoginRequest loginRequest) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
    }

    @Override
    public void registerUser(RegisterRequest registerRequest) throws AddressException {
        if (userRepo.existsUserByEmail(registerRequest.getEmail())||activationTokenRepo.existsActivationTokenByEmail(registerRequest.getEmail()))
            throw new EmailAlreadyExist("Email : " + registerRequest.getEmail()+" already exists!");
        validateEmail(registerRequest.getEmail());
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        ActivationToken activationToken = userMapper.toActivationToken(registerRequest);
        var saved = activationTokenRepo.save(activationToken);
        mailSenderService.sendMessage(activationToken.getEmail(), "Activate your account",
                "localhost:8080/user/activate-account/"+saved.getToken());
    }

    public UserDto activateUser(String token){
        ActivationToken activationToken = activationTokenRepo.findActivationTokenByToken(token).orElseThrow(()->
                new TokenException("The link " + token + " doesn't exist!"));
        if (!isTokenValid(activationToken.getCreatedAt(),ActivationToken.getExpirationInMinutes()))
            throw new TokenException("Link is expired!");
        User user = userMapper.toUserFromToken(activationToken);
        var savedUser = userRepo.save(user);
        activationTokenRepo.delete(activationToken);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public JwtDto login(LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(authentication);
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return userMapper.toJwtDto(userPrincipal,token);

    }

    @Override
    public void sendLinkToResend(String email) {
        User user = userRepo.findUserByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("No user exist with email : "+ email));
        ResetPassword token = userMapper.toResetPassword(user);
        ResetPassword savedToken = resetPasswordRepo.save(token);
        mailSenderService.sendMessage(email,"Reset your password",
                "Click the link below to reset password: \n"+
                "localhost:8080/user/reset-password/"+savedToken.getToken());
    }

    @Override
    public void resetPassword(String token, ResetPasswordRequest passwordRequest) {
        ResetPassword resetPasswordToken = resetPasswordRepo.findResetPasswordByToken(token).orElseThrow(()->
                new TokenException("No such a token exists!"));
        if (!passwordRequest.getPassword().equals(passwordRequest.getRepeatedPassword()))
            throw new RuntimeException("The password does not match each other");
        if (!isTokenValid(resetPasswordToken.getCreatedAt(),ResetPassword.getExpirationInMinutes()))
            throw new TokenException("Link is expired!");
        User user = resetPasswordToken.getUser();
        user.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));
        resetPasswordRepo.delete(resetPasswordToken);
    }

    private boolean isTokenValid(LocalDateTime createdAt, long expiresInMinutes) {
        LocalDateTime deadline = createdAt.plusMinutes(expiresInMinutes);
        return deadline.isAfter(LocalDateTime.now());
    }

}
