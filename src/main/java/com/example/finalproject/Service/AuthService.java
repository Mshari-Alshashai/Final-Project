package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.Model.Developer;
import com.example.finalproject.Model.Game;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.SupportTicket;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.DeveloperRepository;
import com.example.finalproject.Repository.GameRepository;
import com.example.finalproject.Repository.SupportTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final GameRepository gameRepository;
    private final SupportTicketRepository supportTicketRepository;
    private final DeveloperRepository developerRepository;

    public void validateGameForRelease(Integer userId, Integer gameId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) throw new ApiException("User not found");

        Game game = gameRepository.findGameById(gameId);
        if (game == null) throw new ApiException("Game not found");

        game.setValidatedForRelease(true);
        gameRepository.save(game);
    }

    public void validateSupportTicket(Integer userId, Integer ticketId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) throw new ApiException("User not found");

        SupportTicket ticket = supportTicketRepository.findSupportTicketById(ticketId);
        if (ticket == null) throw new ApiException("Ticket not found");

        ticket.setStatus("COMPLETED");
        supportTicketRepository.save(ticket);
    }

    public void validateDeveloper(Integer userId, Integer developerId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) throw new ApiException("User not found");

        Developer developer = developerRepository.findDeveloperById(developerId);
        if (developer == null) throw new ApiException("Developer not found");

        developer.setValidated(true);
        developerRepository.save(developer);
    }
}
