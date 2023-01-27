package delivery.stork.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    Long id;
    TravelerDto travelingDto;
    PackageDto aPackageDto;
    StatusDto statusDto;
    UserDto senderDto;
}
