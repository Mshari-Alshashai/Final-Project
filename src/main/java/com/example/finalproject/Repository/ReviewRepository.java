package com.example.finalproject.Repository;

import com.example.finalproject.Model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    Review findReviewByReviewId(Integer reviewId);
}
