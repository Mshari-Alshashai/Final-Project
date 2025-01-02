package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class Transaction {

    @Id
    private Integer transactionId;

    @Column(unique = true, nullable = false)
    private String orderNumber;

    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String status;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Request request;

    @ManyToOne
    @JsonIgnore
    private Analytics analytics;

}
