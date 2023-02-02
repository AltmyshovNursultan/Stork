package delivery.stork.mapper;

import delivery.stork.model.dto.ReviewDto;
import delivery.stork.model.entity.Review;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {
    private final UserMapper userMapper;
    public Review toReviewFromRequest(User userReviewGet, CommentRequest commentRequest, User userReviewAdder) {
        return Review.builder()
                .userReview(userReviewGet)
                .comment(commentRequest.getText())
                .rate(commentRequest.getRate())
                .userReviewAdder(userReviewAdder)
                .build();
    }
    public ReviewDto toReviewDto(Review review){
        return ReviewDto.builder()
                .rate(review.getRate())
                .id(review.getId())
                .comment(review.getComment())
                .userReview(userMapper.toUserDto(review.getUserReview()))
                .userReviewAdder(userMapper.toUserDto(review.getUserReviewAdder()))
                .build();
    }
}
