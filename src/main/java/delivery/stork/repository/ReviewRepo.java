package delivery.stork.repository;

import delivery.stork.model.entity.Review;
import delivery.stork.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findReviewsByUserReview(User userReviewGetter);
}
