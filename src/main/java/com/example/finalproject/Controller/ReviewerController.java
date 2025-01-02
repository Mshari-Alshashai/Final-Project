package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.DTO.DeveloperIDTO;
import com.example.finalproject.DTO.ReviewerIDTO;
import com.example.finalproject.Model.Developer;
import com.example.finalproject.Model.Reviewer;
import com.example.finalproject.Service.ReviewerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviewer")
public class ReviewerController {
    private final ReviewerService reviewerService;

    @GetMapping("/get-reviewer/{userId}")
    public ResponseEntity<Reviewer> getMyReviewer(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(reviewerService.getMyReviewer(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid ReviewerIDTO reviewerIDTO) {
        reviewerService.register(reviewerIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully registered"));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateReviewer(@PathVariable Integer userId, @RequestBody @Valid ReviewerIDTO reviewerIDTO) {
        reviewerService.updateReviewer(userId, reviewerIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteReviewer(@PathVariable Integer userId) {
        reviewerService.deleteReviewer(userId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted"));
    }


}
