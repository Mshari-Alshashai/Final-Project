package com.example.finalproject.Repository;

import com.example.finalproject.Model.Developer;
import com.example.finalproject.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    Developer findDeveloperById(Integer id);





}
