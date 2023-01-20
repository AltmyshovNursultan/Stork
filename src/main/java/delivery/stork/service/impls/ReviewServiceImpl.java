package delivery.stork.service.impls;

import delivery.stork.exception.NotAllowedException;
import delivery.stork.exception.NotFoundException;
import delivery.stork.mapper.ReviewMapper;
import delivery.stork.model.dto.ReviewDto;
import delivery.stork.model.entity.Review;
import delivery.stork.model.entity.User;
import delivery.stork.model.wrapper.CommentRequest;
import delivery.stork.repository.ReviewRepo;
import delivery.stork.repository.UserRepo;
import delivery.stork.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;
    private final ReviewMapper reviewMapper;


    @Override
    public void deleteReview(Long id, User user) {
        Review review = findReviewById(id);
        if (!review.getUserReviewAdder().equals(user)){
            throw new NotAllowedException("You are allowed to delete other's review");
        }
        reviewRepo.delete(review);
    }

    @Override
    public void writeComment(Long userReview, CommentRequest commentRequest, User userReviewAdder) {
        User userReviewGet = userRepo.findById(userReview).orElseThrow(()->
                new NotFoundException("No such a user found with id " + userReview));
        Review review = reviewMapper.toReviewFromRequest(userReviewGet, commentRequest, userReviewAdder);
        reviewRepo.save(review);
        double averageRate = countAverageRate(userReviewGet);
        userReviewGet.setAverageRate(averageRate);
    }

    @Override
    public List<ReviewDto> findReviewsOfUser(Long userReviewId) {
        User userReviewGet = userRepo.findById(userReviewId).orElseThrow(()->
                new NotFoundException("No such a user found with id " + userReviewId));
        List<Review> reviewList = reviewRepo.findReviewsByUserReview(userReviewGet);
        return reviewList.stream().map(reviewMapper::toReviewDto)
                .collect(Collectors.toList());
    }

    private double  countAverageRate(User userReviewGet){
        List<Review> getListOfReviewByGetter = reviewRepo.findReviewsByUserReview(userReviewGet);
        double result =0;
        for (int i = 0 ; i < getListOfReviewByGetter.size(); i++ ){
            result += getListOfReviewByGetter.get(i).getRate();
        }
        return result;
    }
    private Review findReviewById(Long id){
        return reviewRepo.findById(id).orElseThrow(()-> new NotFoundException(" No such review found with ID " + id));
    }

}
