package delivery.stork.controller;

import delivery.stork.model.dto.UserDto;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.EditUserRequest;
import delivery.stork.model.wrapper.LoginRequest;
import delivery.stork.model.wrapper.RegisterRequest;
import delivery.stork.model.wrapper.ResetPasswordRequest;
import delivery.stork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) throws AddressException {
        userService.registerUser(registerRequest);
        return ResponseEntity.ok("Activation link was sent, activate your account to complete registration!");
    }
    @GetMapping("/activate-account/{token}")
    public ResponseEntity<?> createUser(@PathVariable String token) {
        userService.activateUser(token);
        return ResponseEntity.ok("Your account has been activated!");
    }
    @PostMapping("/password/reset")
    public ResponseEntity<?> sendPasswordResetLink(@Valid @RequestParam String email) {
        userService.sendLinkToResend(email);
        return ResponseEntity.ok("Link was sent to your email to reset your password!");
    }
    @PostMapping("/reset-password/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token,
                                           @Valid @RequestBody ResetPasswordRequest resetRequest) {
        userService.resetPassword(token, resetRequest);
        return ResponseEntity.ok("You password has been successfully reset");
    }
    @GetMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.login(loginRequest));
    }
    @PostMapping("/edit")
    ResponseEntity<UserDto> editUser(@Valid @RequestBody EditUserRequest editUserRequest,
                                     @AuthenticationPrincipal User user){
        return ResponseEntity.ok(userService.updateUser(editUserRequest,user));
    }

}
