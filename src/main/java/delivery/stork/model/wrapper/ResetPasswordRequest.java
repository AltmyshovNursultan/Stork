package delivery.stork.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetPasswordRequest {
    @JsonProperty("password")
    @NotBlank
    private String password;

    @NotBlank
    @JsonProperty("repeated_password")
    private String repeatedPassword;
}
