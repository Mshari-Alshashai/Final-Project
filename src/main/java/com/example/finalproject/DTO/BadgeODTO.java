package com.example.finalproject.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadgeODTO {

    private String name;
    private String description;

}
