package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.GameIDTO;
import com.example.finalproject.DTO.GameODTO;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final AuthRepository authRepository;
    private final TagRepository tagRepository;
    private final GenreRepository genreRepository;
    private final BadgeRepository badgeRepository;



    public List<GameODTO> getAllGames(){
        return convertGameToGameODTO(gameRepository.findAll());
    }

    public void addGame(Integer developerId, GameIDTO gameIDTO){
        Developer developer = developerRepository.findDeveloperById(developerId);
        if(developer ==null){
            throw new ApiException("Developer not found");
        }

        Game game =convertGameIDToToGame(gameIDTO);
        if(gameRepository.findGamesByDeveloper(developer).contains(game)){
            throw new ApiException("This game is already exist");
        }
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

        if(!Objects.equals(oldGame.getDeveloper().getId(), myUser.getId())){
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

    public void applyDiscount(Integer userId, Integer gameId, Double discountPercentage) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null)throw new ApiException("User not found");

        Game game = gameRepository.findGameById(gameId);

        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new ApiException("Discount must be between 0 and 100");
        }

        if (game.getOriginalPrice() == null) {
            game.setOriginalPrice(game.getPrice());
        }

        game.setPrice(game.getPrice() * (1 - discountPercentage / 100));
        gameRepository.save(game);

    }

    public void removeDiscount(Integer userId, Integer gameId) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) throw new ApiException("User not found");

        Game game = gameRepository.findGameById(gameId);
        if (game == null) throw new ApiException("Game not found");

        if (game.getOriginalPrice() == null) {
            throw new ApiException("No discount applied to this game");
        }

        game.setPrice(game.getOriginalPrice());
        game.setOriginalPrice(null);
        gameRepository.save(game);
    }

    public List<Game> findSimilarGames(Integer userId, Integer gameId) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null)throw new ApiException("User not found");

        Game game = gameRepository.findGameById(gameId);
        if(game ==null)throw new ApiException("Game not found");

        return gameRepository.findGamesByGenre(game.getGenre());
    }

    public void assignTagToGame(Integer userId, Integer gameId, Integer tagId) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null)throw new ApiException("User not found");

        Game game = gameRepository.findGameById(gameId);
        if (game == null) throw new ApiException("Game not found");

        Tag tag = tagRepository.findTagById(tagId);
        if (tag == null) throw new ApiException("Tag not found");

        game.getTags().add(tag);
        gameRepository.save(game);
    }

    public void assignGenreToGame(Integer userId, Integer gameId, Integer genreId) {
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null)throw new ApiException("User not found");

        Game game = gameRepository.findGameById(gameId);
        if (game == null) throw new ApiException("Game not found");

        Genre genre = genreRepository.findGenreById(genreId);
        if (genre == null) throw new ApiException("Genre not found");

        game.setGenre(genre);
        gameRepository.save(game);
    }



    public Game convertGameIDToToGame(GameIDTO gameIDTO){
        return new Game(null,gameIDTO.getName(),gameIDTO.getPrice(),null,null,false,gameIDTO.getSize(),
                null,null,null,null,null,null,null,null,null,null,null);
    }

    public List<GameODTO> convertGameToGameODTO(List<Game> games){
        List<GameODTO> gameODTOS = new ArrayList<>();
            for (Game game: games){
                gameODTOS.add(new GameODTO(game.getName(),game.getPrice(),game.getReleaseDate(),game.getSize()));
            }
            return gameODTOS;
    }




}
