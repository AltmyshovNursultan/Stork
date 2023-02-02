package delivery.stork.service;

import delivery.stork.model.dto.ReviewDto;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.CommentRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewService {
    void deleteReview(Long id, User user);
    void writeComment(@RequestParam Long userReview, @RequestBody CommentRequest commentRequest,
                      @AuthenticationPrincipal User userReviewAdder);
    List<ReviewDto> findReviewsOfUser(Long userReviewId);
}
