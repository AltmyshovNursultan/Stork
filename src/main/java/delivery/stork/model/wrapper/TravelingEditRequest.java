package delivery.stork.model.wrapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TravelingEditRequest {
    @NonNull
    String getFrom;
    @NonNull
    String getTo;
    @NonNull

    LocalDateTime flightDate;
    @NonNull
    LocalDateTime arrivalDate;
    @NonNull
    double capacity;
    @NonNull
    double price;
}
