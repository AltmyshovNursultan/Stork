package delivery.stork.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetPasswordRequest {
    @JsonProperty("password")
    @NotBlank
    private String password;

    @NotBlank
    @JsonProperty("repeated_password")
    private String repeatedPassword;
}
