package delivery.stork.model.wrapper;

import delivery.stork.model.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageRequest {

    @NotBlank
    String nameOfPackage;
    @NotBlank
    String from;
    @NotBlank
    String to;
    @NotBlank
    double weight;
    @NotBlank
    LocalDateTime startDate;
    @NotBlank
    LocalDateTime deadline;
    @NotBlank
    double price;
    @NotBlank
    User senderPackage;
}
