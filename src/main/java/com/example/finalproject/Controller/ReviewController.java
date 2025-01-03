package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.DTO.ReviewIDTO;
import com.example.finalproject.Model.Review;
import com.example.finalproject.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/get-reviews/{UserId}")
    public ResponseEntity<List<Review>> getMyReviews(@PathVariable Integer UserId){
       return ResponseEntity.status(200).body(reviewService.getMyReviews(UserId));
    }

    @PostMapping("/add-review/{userId}")
    public ResponseEntity<ApiResponse> addReview(@PathVariable Integer userId, @RequestBody ReviewIDTO reviewIDTO){
        reviewService.addReview(userId,reviewIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Review added successfully"));
    }

    @PutMapping("/update-review/{userId}/{reviewId}")
    public ResponseEntity<ApiResponse> updateReview(@PathVariable Integer userId, @PathVariable Integer reviewId , @RequestBody ReviewIDTO reviewIDTO){
        reviewService.updateReview(userId,reviewId,reviewIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Review updated successfully"));
    }

    @DeleteMapping("/delete-review/{userId}/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Integer userId, @PathVariable Integer reviewId){
        reviewService.deleteReview(userId,reviewId);
        return ResponseEntity.status(200).body(new ApiResponse("Review deleted successfully"));
    }

    @GetMapping("/calculate-avg-rating/{gameId}")
    public ResponseEntity calculateAverageRating(@PathVariable Integer gameId){
        return ResponseEntity.status(200).body(reviewService.calculateAverageRating(gameId));
    }




}
