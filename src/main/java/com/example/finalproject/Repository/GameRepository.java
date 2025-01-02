package com.example.finalproject.Repository;

import com.example.finalproject.Model.Badge;
import com.example.finalproject.Model.Developer;
import com.example.finalproject.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer> {
    Game findGameById(Integer id);
    boolean existsByDeveloper(Developer developer);

    @Query("SELECT g FROM Game g JOIN g.badges b WHERE b.badgeId = :badgeId")
    List<Game> findGamesByBadgeId(@Param("badgeId") Integer badgeId);

}
