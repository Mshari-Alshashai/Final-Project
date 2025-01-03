package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.Model.Game;
import com.example.finalproject.Model.Player;
import com.example.finalproject.Model.Wishlist;
import com.example.finalproject.Repository.GameRepository;
import com.example.finalproject.Repository.PlayerRepository;
import com.example.finalproject.Repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

//    public WishlistODTO getAllWishlists(Integer playerId) {
//
//        Wishlist wishlist = wishlistRepository.findWishlistById(playerId);
//
//        return getWishlistById(wishlist);
//
//    }
//
//public WishlistODTO getWishlistById(Wishlist wishlist) {
//
//        WishlistODTO wishlistODTO = new WishlistODTO();
//
//        wishlistODTO.setGames(wishlist.getGames());
//
//        return wishlistODTO;
//}


    public void addGameToWishlist(Integer userId, Integer gameId) {
        Wishlist wishlist = wishlistRepository.findWishlistByPlayerId(userId);
        if (wishlist == null) createNewWishlist(userId);

        Game game = gameRepository.findGameById(gameId);
        if (game == null) throw new ApiException("Game not found");

        if (!wishlist.getGames().contains(game)) {
            wishlist.getGames().add(game);
        }

        wishlistRepository.save(wishlist);
    }

    public void removeGameFromWishlist(Integer userId, Integer gameId) {
        Wishlist wishlist = wishlistRepository.findWishlistByPlayerId(userId);
        if (wishlist == null) throw new ApiException("Wishlist not found");

        Game game = gameRepository.findGameById(gameId);
        if (game == null) throw new ApiException("Game not found");

        wishlist.getGames().remove(game);

        wishlistRepository.save(wishlist);
    }

    private void createNewWishlist(Integer playerId) {
        Wishlist wishlist = new Wishlist();
        Player player = playerRepository.findPlayerById(playerId);

        wishlist.setPlayer(player);
        wishlistRepository.save(wishlist);
    }



}
