package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.GameIDTO;
import com.example.finalproject.DTO.GameODTO;
import com.example.finalproject.DTO.PlayerIDTO;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.Objects;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final AuthRepository authRepository;
    private final TagRepository tagRepository;
    private final GenreRepository genreRepository;
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
