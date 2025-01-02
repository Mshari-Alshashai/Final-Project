package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.GameIDTO;
import com.example.finalproject.DTO.GameODTO;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.BadgeRepository;
import com.example.finalproject.Repository.DeveloperRepository;
import com.example.finalproject.Repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final AuthRepository authRepository;
    private final BadgeRepository badgeRepository;



    public List<GameODTO> getAllGames(){
        return convertGameToGameODTO(gameRepository.findAll());
    }


    public void addGame(Integer developerId, GameIDTO gameIDTO){
        Developer developer = developerRepository.findDeveloperById(developerId);
        if(developer ==null){
            throw new ApiException("Developer not found");
        }
        if(gameRepository.existsByDeveloper(developer)){
            throw new ApiException("This game is already exist");
        }
        Game game =convertGameIDToToGame(gameIDTO);
        game.setDeveloper(developer);
        gameRepository.save(game);

    }

    public void updateGame(Integer developerId, Integer gameId ,GameIDTO gameIDTO){
        Developer developer = developerRepository.findDeveloperById(developerId);
        if(developer==null){
            throw new ApiException("Developer not found");}

        MyUser myUser =authRepository.findMyUserById(developerId);
        if(myUser ==null){
            throw new ApiException("User not found");
        }

        Game oldGame =gameRepository.findGameById(gameId);
        if(oldGame ==null){
            throw new ApiException("Game not found");
        }

        if(oldGame.getDeveloper().getId() != myUser.getId()){
            throw new ApiException("This is not your game");
        }

        oldGame.setName(gameIDTO.getName());
        oldGame.setPrice(gameIDTO.getPrice());
        oldGame.setSize(gameIDTO.getSize());
        gameRepository.save(oldGame);
    }


    public void deleteGame(Integer developerId, Integer gameId){
        Developer developer = developerRepository.findDeveloperById(developerId);
        MyUser myUser = authRepository.findMyUserById(developerId);
        if(developer ==null || myUser ==null){
            throw new ApiException("Developer not found");
        }
        Game game = gameRepository.findGameById(gameId);
        if(game ==null){
            throw new ApiException("Game not found");
        }
        gameRepository.delete(game);
    }


    public List<Game> findGamesByBadgeName(Integer badgeId) {
        return gameRepository.findGamesByBadgeId(badgeId);
    }

public Game convertGameIDToToGame(GameIDTO gameIDTO){
        Game game = new Game(null,gameIDTO.getName(),gameIDTO.getPrice(), LocalDate.now(),gameIDTO.getSize(),
                null,null,null,null,null,null,null,null,null);

        return game;
}

    public List<GameODTO> convertGameToGameODTO(List<Game> games){
        List<GameODTO> gameODTOS = new ArrayList<>();
            for (Game game: games){
                gameODTOS.add(new GameODTO(game.getName(),game.getPrice(),game.getReleaseDate(),game.getSize()));
            }
            return gameODTOS;
    }




}
