package delivery.stork.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "travelers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Traveler {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String getFrom;
    String getTo;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDateTime flightDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDateTime arrivalDate;
    double capacity;
    double price;
    @OneToOne(cascade = CascadeType.ALL)
            @JoinColumn(name = "user_traveler_id")
    User userTraveler;

}
