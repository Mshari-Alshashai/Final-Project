package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.SupportTicketIDTO;
import com.example.finalproject.DTO.SupportTicketODTO;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Request;
import com.example.finalproject.Model.SupportTicket;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.SupportTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportTicketService {
    private final SupportTicketRepository supportTicketRepository;
    private final AuthRepository authRepository;
    private final  RequestService requestService;


    public List<SupportTicketODTO> getAllSupportTicket(){
        return convertTicketToTicketODTO(supportTicketRepository.findAll());
    }

    public void addSupportTicket(Integer userId, SupportTicketIDTO supportTicketIDTO){
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null){
            throw new ApiException("User not found");
        }
        SupportTicket supportTicket = convertIDToToTicket(supportTicketIDTO);
        Request request = new Request(null,"SUPPORT",LocalDateTime.now(),"PENDING",null,null,null,null,supportTicket);
        requestService.addRequest(request);
    }


    public  SupportTicket convertIDToToTicket(SupportTicketIDTO supportTicketIDTO){
        return new SupportTicket(null,supportTicketIDTO.getTitle(),supportTicketIDTO.getDescription(),"PENDING", LocalDateTime.now(),null);
    }

    public List<SupportTicketODTO> convertTicketToTicketODTO(List<SupportTicket> supportTickets){
        List<SupportTicketODTO> supportTicketODTOS = new ArrayList<>();

        for(SupportTicket ticket: supportTickets) {
        supportTicketODTOS.add(new SupportTicketODTO(ticket.getTitle(),ticket.getDescription(),ticket.getStatus(),ticket.getCreationDate()));
        }
        return supportTicketODTOS;
    }

}
