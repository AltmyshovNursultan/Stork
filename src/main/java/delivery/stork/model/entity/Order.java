package delivery.stork.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
            @JoinColumn(name = "traveler_id")
    Traveling traveling;
    @OneToOne
    Package packages;
    @ManyToOne
    User sender;
    @ManyToOne
    Status status;
}