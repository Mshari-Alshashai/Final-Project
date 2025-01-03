package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false)
    @Min(value= 1,message = "Rating can't be less than 1")
    @Max(value= 5,message = "Rating can't be more than 5")
    private Integer rating;


    private LocalDateTime reviewDate;

    @ManyToOne
    @JsonIgnore
    private Player player;

    @ManyToOne
    @JsonIgnore
    private Reviewer reviewer;

    @ManyToOne
    @JsonIgnore
    private Analytics analytics;

    @ManyToOne
    @JsonIgnore
    private Game game;
}
