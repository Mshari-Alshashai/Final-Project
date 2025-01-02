package com.example.finalproject.Controller;

import com.example.finalproject.Service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/platform")
@RequiredArgsConstructor
public class PlatformController {
    private final PlatformService platformService;
}
