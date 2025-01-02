package com.example.finalproject.Repository;

import com.example.finalproject.Model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {
    Badge findBadgeByBadgeId(Integer badgeId);
}
