package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.DTO.PlayerIDTO;
import com.example.finalproject.Model.Player;
import com.example.finalproject.Service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/get-player/{userId}")
    public ResponseEntity<Player> getMyPlayer(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(playerService.getMyPlayer(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid PlayerIDTO playerIDTO){
        playerService.register(playerIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully registered"));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updatePlayer(@PathVariable Integer userId,@RequestBody @Valid PlayerIDTO playerIDTO){
        playerService.updatePlayer(userId, playerIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deletePlayer(@PathVariable Integer userId){
         playerService.deletePlayer(userId);
         return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted"));
    }
}
