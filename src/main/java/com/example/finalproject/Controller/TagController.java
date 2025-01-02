package com.example.finalproject.Controller;

import com.example.finalproject.Service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tag")
public class TagController {
    private final TagService tagService;
}
