package com.example.finalproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameODTO {
    private String name;
    private Double price;
    private LocalDate releaseDate = LocalDate.now();
    private String size;
}
