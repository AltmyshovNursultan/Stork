package delivery.stork.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "full_name",nullable = false)
    String fullName;
    @NonNull
    String email;
    @NonNull
    String password;

    double averageRate;
    @NonNull
    String instagram;
    @CreationTimestamp
    LocalDateTime createdAt;
    @OneToOne(mappedBy = "userTraveler")
    Traveler traveler;
}
