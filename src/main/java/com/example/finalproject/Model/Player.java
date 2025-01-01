package com.example.finalproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {
    @Id
    private Integer id;
    @Column(columnDefinition = "int")
    @Positive(message = "Games purchased must be positive")
    private Integer gamesPurchased=0;
}
