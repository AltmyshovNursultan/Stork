package delivery.stork.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    int rate;
    @NonNull
    String comment;
    @ManyToOne
            @JoinColumn(name = "user_review_id")
    User userReview;
    @ManyToOne
            @JoinColumn(name = "user_review_adder_id")
    User userReviewAdder;
}
