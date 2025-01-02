package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.DTO.GameIDTO;
import com.example.finalproject.Service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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




}
