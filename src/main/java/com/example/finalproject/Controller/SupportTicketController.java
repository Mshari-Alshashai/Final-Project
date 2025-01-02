package com.example.finalproject.Controller;

import com.example.finalproject.Service.SupportTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/support")
public class SupportTicketController {
    private final SupportTicketService supportTicketService;
}
