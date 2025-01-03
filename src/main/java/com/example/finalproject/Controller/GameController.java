package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.DTO.GameIDTO;
import com.example.finalproject.Model.Game;
import com.example.finalproject.Service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game")
public class GameController {
    private final GameService gameService;

    @GetMapping("/get-all-games")
    public ResponseEntity getAllGames(){
        return ResponseEntity.status(200).body(gameService.getAllGames());
    }

    @PostMapping("/add-game/{developerId}")
    public ResponseEntity addGame(@PathVariable Integer developerId,@RequestBody @Valid GameIDTO gameIDTO){
        gameService.addGame(developerId,gameIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Game added"));
    }

    @PutMapping("/update-game/{developerId}/{gameId}")
    public ResponseEntity updateGame(@PathVariable Integer developerId,@PathVariable Integer gameId ,@RequestBody @Valid GameIDTO gameIDTO){
        gameService.updateGame(developerId,gameId,gameIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Game updated"));
    }

    @DeleteMapping("/delete-game/{developerId}/{gameId}")
    public ResponseEntity deleteGame(@PathVariable Integer developerId,@PathVariable Integer gameId){
        gameService.deleteGame(developerId,gameId);
        return ResponseEntity.status(200).body(new ApiResponse("Game deleted"));
    }

    @GetMapping("/get-games-by-badge/{badgeId}")
    public ResponseEntity findGamesByBadgeName(@PathVariable Integer badgeId){
        return ResponseEntity.status(200).body(gameService.findGamesByBadge(badgeId));
    }

    @GetMapping("/recommend-games/{playerId}")
    public ResponseEntity recommendGames(@PathVariable Integer playerId) {
        return ResponseEntity.status(200).body(gameService.recommendGames(playerId));
    }

    @GetMapping("/filter-games-by-price-range/{minPrice}/{maxPrice}")
    public ResponseEntity filterGamesByPriceRange(@PathVariable  Double minPrice,@PathVariable Double maxPrice){
        return ResponseEntity.status(200).body(gameService.filterGamesByPriceRange(minPrice,maxPrice));
    }
    @GetMapping("/find-game-by-developer/{developerId}")
    public ResponseEntity findGamesByDeveloperId(@PathVariable Integer developerId){
        return ResponseEntity.status(200).body(gameService.findGamesByDeveloperId(developerId));
    }


    @PutMapping("/apply-discount/{userId}/{gameId}/{discount}")
    public ResponseEntity applyDiscount(@PathVariable Integer userId, @PathVariable Integer gameId, @PathVariable Double discount){
        gameService.applyDiscount(userId,gameId,discount);
        return ResponseEntity.status(200).body(new ApiResponse("Discount applied"));
    }

    @PutMapping("/remove-discount/{userId}/{gameId}")
    public ResponseEntity removeDiscount(@PathVariable Integer userId, @PathVariable Integer gameId){
        gameService.removeDiscount(userId,gameId);
        return ResponseEntity.status(200).body(new ApiResponse("Discount removed"));
    }

    @GetMapping("/find-similar-games/{userId}/{gameId}")
    public ResponseEntity findSimilarGames(@PathVariable Integer userId,@PathVariable Integer gameId){
        return ResponseEntity.status(200).body(gameService.findSimilarGames(userId,gameId));
    }

    @PutMapping("/assign-tag/{userId}/{gameId}/{tagId}")
    public ResponseEntity assignTagToGame(@PathVariable Integer userId,@PathVariable Integer gameId, @PathVariable Integer tagId){
        gameService.assignTagToGame(userId,gameId,tagId);
        return ResponseEntity.status(200).body(new ApiResponse("Tag assigned"));
    }

    @PutMapping("/assign-genre/{userId}/{gameId}/{genreId}")
    public ResponseEntity assignGenreToGame(@PathVariable Integer userId,@PathVariable Integer gameId, @PathVariable Integer genreId){
        gameService.assignGenreToGame(userId,gameId,genreId);
        return ResponseEntity.status(200).body(new ApiResponse("Genre assigned"));
    }


    @GetMapping("/get-top-games/{limit}")
    public ResponseEntity getTopRatedGames(@PathVariable Integer limit){
        return ResponseEntity.status(200).body(gameService.getTopRatedGames(limit));
    }

//example : GET /games/released-in-range?startDate=2024-01-01&endDate=2024-12-31
    @GetMapping("/released-in-range")
    public ResponseEntity getGamesReleasedInRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Game> games = gameService.getGamesByReleaseDateRange(startDate, endDate);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/recent-release-games/{days}")
    public ResponseEntity findRecentlyReleasedGames(@PathVariable Integer days){
        return ResponseEntity.status(200).body(gameService.findRecentlyReleasedGames(days));
    }


}
