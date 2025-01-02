package com.example.finalproject.Service;

import com.example.finalproject.Model.Badge;
import com.example.finalproject.Repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;

}
