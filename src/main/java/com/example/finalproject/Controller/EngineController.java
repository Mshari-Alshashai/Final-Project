package com.example.finalproject.Controller;

import com.example.finalproject.Service.EngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/engine")
public class EngineController {
    private final EngineService engineService;
}
