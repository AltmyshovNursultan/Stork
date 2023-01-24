package delivery.stork.mapper;

import delivery.stork.model.dto.JwtDto;
import delivery.stork.model.dto.UserDto;
import delivery.stork.model.entity.ActivationToken;
import delivery.stork.model.entity.ResetPassword;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.EditUserRequest;
import delivery.stork.model.wrapper.RegisterRequest;
import delivery.stork.secutiry.impls.UserDetailsImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserMapper {
    public ActivationToken toActivationToken(RegisterRequest registerRequest) {
        return ActivationToken.builder()
                .email(registerRequest.getEmail())
                .instagram(registerRequest.getInstagram())
                .password(registerRequest.getPassword())
                .token(UUID.randomUUID().toString())
                .fullName(registerRequest.getFullName())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public User toUserFromToken(ActivationToken activationToken) {
        return User.builder()
                .fullName(activationToken.getFullName())
                .password(activationToken.getPassword())
                .email(activationToken.getEmail())
                .averageRate(0)
                .instagram(activationToken.getInstagram())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public UserDto toUserDto(User savedUser) {
        return UserDto.builder()
                .id(savedUser.getId())
                .averageRate(savedUser.getAverageRate())
                .email(savedUser.getEmail())
                .fullName(savedUser.getFullName())
                .instagram(savedUser.getInstagram())
                .build();
    }

    public JwtDto toJwtDto(UserDetailsImpl userPrincipal, String token) {
        return JwtDto.builder()
                .id(userPrincipal.getId())
                .tokenType("Bearer")
                .accessToken(token)
                .email(userPrincipal.getUsername())
                .fullName(userPrincipal.getFullName())
                .build();
    }

    public ResetPassword toResetPassword(User user) {
        return ResetPassword.builder()
                .createdAt(LocalDateTime.now())
                .user(user)
                .token(UUID.randomUUID().toString())
                .build();
    }

    public User toUserFromEdit(EditUserRequest editUserRequest,User user) {
        user.setAverageRate(user.getAverageRate());
        user.setPassword(editUserRequest.getPassword());
        user.setEmail(editUserRequest.getEmail());
        user.setFullName(editUserRequest.getFullName());
        user.setInstagram(editUserRequest.getInstagram());
       return user;
    }
}
