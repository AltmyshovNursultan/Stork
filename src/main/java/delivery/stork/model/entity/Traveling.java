package delivery.stork.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "travelers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Traveling {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String getFrom;
    String getTo;

    LocalDateTime flightDate;

    LocalDateTime arrivalDate;
    double capacity;
    double price;
    @OneToOne
    User userTraveler;

}
