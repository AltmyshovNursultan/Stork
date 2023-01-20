package delivery.stork.model.wrapper;

import delivery.stork.model.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageRequest {
    Long id;
    String nameOfPackage;
    String from;
    String to;
    double weight;
    LocalDateTime startDate;
    LocalDateTime deadline;
    double price;
    User senderPackage;
}
