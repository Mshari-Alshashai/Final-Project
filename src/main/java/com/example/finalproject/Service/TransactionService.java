package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.TransactionODTO;
import com.example.finalproject.Model.Game;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Player;
import com.example.finalproject.Model.Transaction;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.GameRepository;
import com.example.finalproject.Repository.PlayerRepository;
import com.example.finalproject.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final AuthRepository authRepository;

    public Double calculateTotalEarnings(Integer userId, Integer gameId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("User not found");

        return transactionRepository.findTransactionsByGame_Id(gameId)
                .stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public TransactionODTO buyGame(Integer userId, Integer gameId) {
        Player player = playerRepository.findPlayerById(userId);
        if(player ==null) throw new ApiException("Player not found");

        Game game = gameRepository.findGameById(gameId);
        if(game ==null) throw new ApiException("Game not found");

        if (player.getGames().contains(game)) throw new ApiException("Player already owns this game.");

        player.getGames().add(game);

        Transaction transaction = new Transaction();
        transaction.setPlayer(player);
        transaction.setGame(game);
        transaction.setAmount(game.getPrice());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setActivationCode(generateCode());
        transaction.setStatus("COMPLETED");

        transactionRepository.save(transaction);
        playerRepository.save(player);
        return convertTransactionToDTO(transaction);
    }



    public String generateCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }

    public TransactionODTO convertTransactionToDTO(Transaction transaction) {
        return new TransactionODTO(transaction.getOrderNumber(),LocalDateTime.now()
                ,transaction.getAmount(),transaction.getStatus(),transaction.getActivationCode());
    }

}
