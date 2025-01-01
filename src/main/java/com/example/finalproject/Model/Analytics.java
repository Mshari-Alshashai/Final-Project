package com.example.finalproject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer totalDownloads;

    @Column(nullable = false)
    private Integer activePlayers;

    @Column(nullable = false)
    private Integer averagePlayTime;

    @Column(nullable = false)
    private Integer averageRating;
}
