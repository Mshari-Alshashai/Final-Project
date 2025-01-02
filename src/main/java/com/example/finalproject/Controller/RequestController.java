package com.example.finalproject.Controller;

import com.example.finalproject.Service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
public class RequestController {
    private final RequestService requestService;
}
