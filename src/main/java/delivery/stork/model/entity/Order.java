package delivery.stork.model.entity;

import delivery.stork.model.enums.StatusOrder;
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
    Package aPackage;
    @Enumerated(EnumType.STRING)
    StatusOrder statusOrder;
}