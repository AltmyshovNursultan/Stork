package delivery.stork.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "packages")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Package {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
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
    @ManyToOne
            @JoinColumn(name = "user_sender_id")
    @NonNull
    User senderPackage;

}
