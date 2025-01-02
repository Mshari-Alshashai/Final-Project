package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.GameODTO;
import com.example.finalproject.DTO.WishlistODTO;
import com.example.finalproject.Model.Game;
import com.example.finalproject.Model.Player;
import com.example.finalproject.Model.Wishlist;
import com.example.finalproject.Repository.PlayerRepository;
import com.example.finalproject.Repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
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














}
