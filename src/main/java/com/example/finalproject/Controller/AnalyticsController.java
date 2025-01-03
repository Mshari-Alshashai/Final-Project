package com.example.finalproject.Controller;

import com.example.finalproject.Service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("/get-analytics-for-game/{userId}/{gameId}")
    public ResponseEntity getAnalyticsForGame(@PathVariable Integer userId, @PathVariable Integer gameId) {
        return ResponseEntity.status(200).body(analyticsService.getAnalyticsForGame(userId, gameId));
    }
}
