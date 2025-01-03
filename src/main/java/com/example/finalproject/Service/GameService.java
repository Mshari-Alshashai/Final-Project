package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.GameIDTO;
import com.example.finalproject.DTO.GameODTO;
import com.example.finalproject.DTO.PlayerIDTO;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final AuthRepository authRepository;
    private final BadgeRepository badgeRepository;
    private final PlayerRepository playerRepository;



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

//Raghad 1
    public List<Game> findGamesByBadge(Integer badgeId) {
        return gameRepository.findGamesByBadgeId(badgeId);
    }


    //Raghad 2
    public List<Game> recommendGames(Integer playerId) {
        // Fetch the player's details
        Player player = playerRepository.findPlayerById(playerId);
        if (player == null) {
            throw new ApiException("Player not found");
        }

        // Get the player's wishlist games
        Set<Game> wishlistGames = player.getWishList().getGames();

        // Get the player's purchased games
        Set<Game> purchasedGames = player.getGames();

        // Get the player's favorite genres based on purchased games
        Set<Genre> favoriteGenres = purchasedGames.stream()
                .map(Game::getGenre)
                .collect(Collectors.toSet());

        // Recommend games from the same genres, excluding already purchased or wishlisted games
        List<Game> recommendedGames = gameRepository.findByGenreIn(favoriteGenres).stream()
                .filter(game -> !purchasedGames.contains(game) && !wishlistGames.contains(game))
                .collect(Collectors.toList());
        recommendedGames.sort(Comparator.comparing(Game::getReleaseDate).reversed());

        return recommendedGames;
    }

    //Raghad 3
    public List<Game> filterGamesByPriceRange(Double minPrice, Double maxPrice) {
        return gameRepository.findByPriceBetween(minPrice, maxPrice);
    }

    //Raghad 4
    public List<Game> findGamesByDeveloperId(Integer developerId) {
        return gameRepository.findAllByDeveloper_Id(developerId);
    }

//Raghad 6
    public List<Game> getTopRatedGames(Integer limit) {
        // Fetch all games
        List<Game> games = gameRepository.findAll();

        // Calculate the average rating for each game and sort by highest rating
        List<Game> sortedGames = games.stream()
                .sorted((g1, g2) -> Double.compare(
                        g2.getReviews().stream().mapToInt(Review::getRating).average().orElse(0.0),
                        g1.getReviews().stream().mapToInt(Review::getRating).average().orElse(0.0)
                ))
                .limit(limit)
                .collect(Collectors.toList());

        return sortedGames;
    }

    //Raghad 7
    public List<Game> getGamesByReleaseDateRange(LocalDate startDate, LocalDate endDate) {
        // Fetch games released between the specified dates
        return gameRepository.findByReleaseDateBetween(startDate, endDate);
    }

//Raghad 8
    public List<Game> findRecentlyReleasedGames(Integer days) {
        // Calculate the start date by subtracting 'days' from the current date
        LocalDate startDate = LocalDate.now().minusDays(days);

        // Fetch games released after the calculated date
        return gameRepository.findByReleaseDateAfter(startDate);
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
