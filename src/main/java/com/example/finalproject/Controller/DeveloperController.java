package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.DTO.DeveloperIDTO;
import com.example.finalproject.DTO.GameODTO;
import com.example.finalproject.Model.Developer;
import com.example.finalproject.Service.DeveloperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/developer")
@RequiredArgsConstructor
public class DeveloperController {
    private final DeveloperService developerService;

    @GetMapping("/get-developer/{userId}")
    public ResponseEntity<Developer> getMyDeveloper(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(developerService.getMyDeveloper(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid DeveloperIDTO developerIDTO) {
        developerService.register(developerIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully registered"));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateDeveloper(@PathVariable Integer userId, @RequestBody @Valid DeveloperIDTO developerIDTO) {
        developerService.updateDeveloper(userId, developerIDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteDeveloper(@PathVariable Integer userId) {
         developerService.deleteDeveloper(userId);
         return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted"));
    }




    @GetMapping("/get-all-my-games/{userId}")
    public ResponseEntity<List<GameODTO>> getAllMyGames(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(developerService.getAllMyGames(userId));
    }

    @GetMapping("/search-my-game/{userId}/{gameName}")
    public ResponseEntity<GameODTO> searchMyGame(@PathVariable Integer userId, @PathVariable String gameName) {
        return ResponseEntity.status(200).body(developerService.searchMyGame(userId, gameName));
    }

    @GetMapping("/average-rating/{userId}/{gameId}")
    public ResponseEntity<Double> getAverageRatingForGame(@PathVariable Integer userId, @PathVariable Integer gameId) {
        return ResponseEntity.status(200).body(developerService.getAverageRatingForGame(userId, gameId));
    }
}
