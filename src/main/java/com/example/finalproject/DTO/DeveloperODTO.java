package com.example.finalproject.DTO;

import com.example.finalproject.Model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeveloperODTO {



    private String bio;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;


    private List<Platform> platforms;

    private List<Engine> engines;

    private List<Request> requests;

    private List<GameODTO> games;
}
