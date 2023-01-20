package delivery.stork.model.dto;

import delivery.stork.model.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageDto {
    Long id;
    String nameOfPackage;
    String from;
    String to;
    double wight;
    LocalDateTime startDate;
    LocalDateTime deadline;
    double price;
    UserDto userDto;
}
