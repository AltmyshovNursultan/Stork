package delivery.stork.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "activation_tokens")
public class ActivationToken {
    private static final long TOKEN_EXPIRATION_IN_MINUTES = 1440; //24 hours
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "instagram", nullable = false)
    private String instagram;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Transient
    public static long getExpirationInMinutes() {
        return TOKEN_EXPIRATION_IN_MINUTES;
    }
}
