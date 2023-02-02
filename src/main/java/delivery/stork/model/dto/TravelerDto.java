package delivery.stork.model.dto;

import delivery.stork.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelerDto {
    Long id;
    String getFrom;
    String getTo;
    LocalDateTime flightDate;
    LocalDateTime arrivalDate;
    double capacity;
    double price;
    UserDto userTravelerDto;
}
