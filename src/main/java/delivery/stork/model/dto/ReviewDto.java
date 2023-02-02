package delivery.stork.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    Long id;
    int rate;
    String comment;

    UserDto userReview;

    UserDto userReviewAdder;
}
