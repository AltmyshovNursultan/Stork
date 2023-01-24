package delivery.stork.model.dto;


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
