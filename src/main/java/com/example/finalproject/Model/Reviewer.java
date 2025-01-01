package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reviewer {

    @Id
    private Integer reviewerId;

    @Column(nullable = false)
    private String profileUrl;

    @Column(nullable = false)
    private String bio;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "reviewer")
    private Set<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewer")
    private Set<Request> requests;

}
