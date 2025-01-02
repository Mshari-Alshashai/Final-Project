package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.ReviewerIDTO;
import com.example.finalproject.Model.Developer;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Reviewer;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.ReviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewerService {
    private final ReviewerRepository reviewerRepository;
    private final AuthRepository authRepository;

    public Reviewer getMyReviewer(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        if (user.getReviewer() == null) throw new ApiException("developer not found");

        return user.getReviewer();
    }

    public void register(ReviewerIDTO reviewerIDTO) {
        Reviewer reviewer = convertReviewerIDTOToReviewer(reviewerIDTO);
        reviewerRepository.save(reviewer);
    }

    public void updateReviewer(Integer userId, ReviewerIDTO reviewerIDTO) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Reviewer oldReviewer = reviewerRepository.findReviewerByReviewerId(userId);
        if(oldReviewer == null) throw new ApiException("reviewer not found");

        user.setUsername(reviewerIDTO.getUsername());
        user.setPassword(reviewerIDTO.getPassword());
        user.setName(reviewerIDTO.getName());
        user.setEmail(reviewerIDTO.getEmail());
        user.setPhoneNumber(reviewerIDTO.getPhoneNumber());
        oldReviewer.setBio(reviewerIDTO.getBio());
        oldReviewer.setMyUser(user);

        reviewerRepository.save(oldReviewer);


    }

    public void deleteReviewer(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Reviewer oldReviewer = reviewerRepository.findReviewerByReviewerId(userId);
        if(oldReviewer == null) throw new ApiException("reviewer not found");

        oldReviewer.setMyUser(null);

        reviewerRepository.delete(oldReviewer);
        authRepository.delete(user);
    }

    public Reviewer convertReviewerIDTOToReviewer(ReviewerIDTO reviewerIDTO) {
        Reviewer reviewer = new Reviewer();
        MyUser myUser = new MyUser(null, reviewerIDTO.getUsername(), reviewerIDTO.getPassword(), reviewerIDTO.getName()
                , reviewerIDTO.getEmail(), reviewerIDTO.getPhoneNumber(), "REVIEWER",null,null,null);

        reviewer.setMyUser(myUser);
        reviewer.setBio(reviewerIDTO.getBio());

        authRepository.save(myUser);
        return reviewer;
    }
}
