package com.example.finalproject.Repository;

import com.example.finalproject.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
    Player findPlayerById(Integer id);
}
