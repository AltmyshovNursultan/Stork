package delivery.stork.model.wrapper;

import delivery.stork.model.dto.TravelerDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchRequest {
    String fromCity;
    String toCity;
//    LocalDateTime startDate;
//    LocalDateTime endDate;


}
