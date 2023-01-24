package delivery.stork.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageEditRequest {
    @Column(name = "name_of_package", nullable = false)
    String nameOfPackage;
    @Column(name = "get_from", nullable = false)
    String from;
    @Column(name = "get_to", nullable = false)
    String to;
    @Column(name = "weight", nullable = false)
    double weight;
    @Column(name = "start_date", nullable = false)
    LocalDateTime startDate;
    @Column(name = "deadline", nullable = false)
    LocalDateTime deadline;
    @Column(name = "price", nullable = false)
    double price;
}
