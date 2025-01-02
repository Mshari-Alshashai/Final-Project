package com.example.finalproject.Repository;

import com.example.finalproject.Model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    Review findReviewByReviewId(Integer reviewId);

    List<Review> findByGameId(Integer gameId);

    List<Review> findReviewsByPlayer_Id(Integer playerId);
}
