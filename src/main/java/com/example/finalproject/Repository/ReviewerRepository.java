package com.example.finalproject.Repository;

import com.example.finalproject.Model.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {
    Reviewer findReviewerByReviewerId(Integer reviewerId);
}
