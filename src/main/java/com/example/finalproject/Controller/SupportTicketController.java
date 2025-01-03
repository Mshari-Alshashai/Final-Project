package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.DTO.SupportTicketIDTO;
import com.example.finalproject.Service.SupportTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/support")
public class SupportTicketController {
    private final SupportTicketService supportTicketService;

    @GetMapping("/get-all-support")
    public ResponseEntity getAllSupportTicket(){
        return ResponseEntity.status(200).body(supportTicketService.getAllSupportTicket());
    }

    @PostMapping("/send-support-ticket/{userId}")
   public ResponseEntity addSupportTicket(@PathVariable Integer userId,@RequestBody @Valid SupportTicketIDTO supportTicketIDTO){
        supportTicketService.addSupportTicket(userId,supportTicketIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Support Ticket Send successfully"));
   }


}
