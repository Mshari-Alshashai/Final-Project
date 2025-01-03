package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PutMapping("/game-release/{userId}/{gameId}")
    public ResponseEntity validateGameForRelease(@PathVariable Integer userId, @PathVariable Integer gameId) {
        authService.validateGameForRelease(userId, gameId);
        return ResponseEntity.status(200).body(new ApiResponse("Game successfully validated"));
    }

    @PutMapping("/support-ticket/{userId}/{ticketId}")
    public ResponseEntity validateSupportTicket(@PathVariable Integer userId, @PathVariable Integer ticketId) {
        authService.validateSupportTicket(userId, ticketId);
        return ResponseEntity.status(200).body(new ApiResponse("Ticket successfully validated"));
    }

    @PutMapping("/validate-developer/{userId}/{developerId}")
    public ResponseEntity validateDeveloper(@PathVariable Integer userId, @PathVariable Integer developerId) {
        authService.validateDeveloper(userId, developerId);
        return ResponseEntity.status(200).body(new ApiResponse("Developer successfully validated"));
    }
}
