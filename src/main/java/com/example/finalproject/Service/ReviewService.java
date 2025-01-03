package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.ReviewIDTO;
import com.example.finalproject.DTO.ReviewerIDTO;
import com.example.finalproject.Model.Game;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Review;
import com.example.finalproject.Model.Reviewer;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.GameRepository;
import com.example.finalproject.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final AuthRepository authRepository;
    private final GameRepository gameRepository;

    public List<Review> getMyReviews(Integer userId) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) throw new ApiException("user not found");

        return reviewRepository.findReviewsByPlayer_Id(userId);
    }

    public void addReview(Integer userId,ReviewIDTO reviewIDTO) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) throw new ApiException("user not found");

        Review review = convertReviewIDTOToReview(reviewIDTO);
        review.setReviewDate(LocalDateTime.now());

        reviewRepository.save(review);
    }

    public void updateReview(Integer userId,Integer reviewId, ReviewIDTO reviewIDTO) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) throw new ApiException("user not found");

        Review oldReview = reviewRepository.findReviewByReviewId(reviewId);

        oldReview.setReviewText(reviewIDTO.getReviewText());
        oldReview.setRating(reviewIDTO.getRating());

        reviewRepository.save(oldReview);
    }

    public void deleteReview(Integer userId, Integer reviewId) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) throw new ApiException("user not found");

        Review review = reviewRepository.findReviewByReviewId(reviewId);
        reviewRepository.delete(review);
    }



    public Review convertReviewIDTOToReview(ReviewIDTO reviewIDTO) {
        Review review = new Review();

        review.setReviewText(reviewIDTO.getReviewText());
        review.setRating(reviewIDTO.getRating());

        return review;
    }


    //Raghad 5
    public Double calculateAverageRating(Integer gameId) {
        // Fetch the game details
        Game game = gameRepository.findGameById(gameId);
        if(game==null){throw new ApiException("Game not found");}

        // Get the game's reviews
        Set<Review> reviews = game.getReviews();

        // Check if there are reviews
        if (reviews.isEmpty()) {
            return 0.0; // Return 0 if no reviews exist
        }

        // Calculate the average rating
        double averageRating = reviews.stream()
                .mapToInt(Review::getRating) // Extract ratings
                .average()                   // Calculate average
                .orElse(0.0);                // Default to 0.0 if no values

        return averageRating;
    }


}
