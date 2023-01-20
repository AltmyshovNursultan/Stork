package delivery.stork.controller;

import delivery.stork.model.dto.ReviewDto;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.CommentRequest;
import delivery.stork.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestParam Long userReview, @RequestBody CommentRequest commentRequest,
                                       @AuthenticationPrincipal User userReviewAdder){
        reviewService.writeComment(userReview,commentRequest,userReviewAdder);
        return ResponseEntity.ok("You successfully added review");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteReview(@RequestParam Long id, @AuthenticationPrincipal User userReviewAdder){
        reviewService.deleteReview(id,userReviewAdder);
        return ResponseEntity.ok("You successfully delete review");
    }
    @GetMapping("/reviewsofuser")
    ResponseEntity<List<ReviewDto>> getReviewsOfUser(@RequestParam Long userReviewsId){
        return ResponseEntity.ok(reviewService.findReviewsOfUser(userReviewsId));
    }
}
