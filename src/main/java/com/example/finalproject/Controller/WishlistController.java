package com.example.finalproject.Controller;

import com.example.finalproject.Service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/add-game-to-wishlist/{userId}/{gameId}")
    public ResponseEntity addGameToWishlist(@PathVariable Integer userId, @PathVariable Integer gameId) {
        wishlistService.addGameToWishlist(userId, gameId);
        return ResponseEntity.status(200).body("Game added to wishlist");
    }

    @DeleteMapping("/remove-game-from-wishlist/{userId}/{gameId}")
    public ResponseEntity removeGameFromWishlist(@PathVariable Integer userId, @PathVariable Integer gameId) {
        wishlistService.removeGameFromWishlist(userId, gameId);
        return ResponseEntity.status(200).body("Game removed from Wishlist");
    }
}
