package com.example.finalproject.Service;

import com.example.finalproject.DTO.BadgeODTO;
import com.example.finalproject.Model.Badge;
import com.example.finalproject.Model.Game;
import com.example.finalproject.Model.Badge;
import com.example.finalproject.Repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;

//
//    public List<BadgeODTO> getAllBadges() {
//        return listToDTO(badgeRepository.findAll());
//    }
//
//
//    public List<BadgeODTO> listToDTO(List<Badge> badges){
//
//        List<BadgeODTO> badgeODTO = new ArrayList<>() ;
//
//        for (Badge badge1 : badges){
//            BadgeODTO badgeODTO1 = new BadgeODTO();
//
//            badgeODTO1.setGames(badge1.getGames());
//            badgeODTO1.setName(badge1.getName());
//            badgeODTO1.setDescription(badge1.getDescription());
//            badgeODTO.add(badgeODTO1);
//        }
//        return badgeODTO;
//
//    }




}
