package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/calculate-total-earnings/{userId}/{gameId}")
    public ResponseEntity calculateTotalEarnings(@PathVariable Integer userId, @PathVariable Integer gameId) {
        return ResponseEntity.status(200).body(transactionService.calculateTotalEarnings(userId, gameId));
    }

    public ResponseEntity buyGame(@PathVariable Integer userId, @PathVariable Integer gameId) {

        return ResponseEntity.status(200).body(transactionService.buyGame(userId, gameId));
    }
}
