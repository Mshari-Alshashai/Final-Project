package com.example.finalproject.Repository;

import com.example.finalproject.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    Wishlist findWishlistById(Integer id);
}
