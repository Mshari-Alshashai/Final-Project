package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.AnalyticsODTO;
import com.example.finalproject.Model.Analytics;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Repository.AnalyticsRepository;
import com.example.finalproject.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final AnalyticsRepository analyticsRepository;
    private final AuthRepository authRepository;


    public AnalyticsODTO convertToODTO(Analytics analytics) {
        return new AnalyticsODTO(analytics.getTotalDownloads(),analytics.getActivePlayers(),analytics.getAveragePlayTime(),analytics.getAverageRating());

    }
}
