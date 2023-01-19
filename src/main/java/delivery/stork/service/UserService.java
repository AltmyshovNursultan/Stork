package delivery.stork.service;

import delivery.stork.model.dto.JwtDto;
import delivery.stork.model.dto.UserDto;
import delivery.stork.model.entity.ResetPassword;
import delivery.stork.model.wrapper.LoginRequest;
import delivery.stork.model.wrapper.RegisterRequest;
import delivery.stork.model.wrapper.ResetPasswordRequest;

import javax.mail.internet.AddressException;

public interface UserService {
    public void registerUser(RegisterRequest registerRequest) throws AddressException;
    UserDto activateUser(String token);
    JwtDto login(LoginRequest loginRequest);
    void sendLinkToResend(String email);
    void resetPassword(String token, ResetPasswordRequest passwordRequest);


}
