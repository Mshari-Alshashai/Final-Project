package com.example.finalproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Empty name")
    @Column(nullable = false, unique = true)
    private String name;
    @Column(columnDefinition = "DOUBLE not null")
    @NotNull(message = "Empty balance")
    @PositiveOrZero(message = "Price must be positive")
    private Double price;
    @Column(columnDefinition = "date")
    private LocalDate releaseDate = LocalDate.now();
    @NotEmpty(message = "Empty name")
    @Column(nullable = false)
    private String size;
}
