package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.PlayerIDTO;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Player;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final AuthRepository authRepository;

    public void register(PlayerIDTO playerIDTO) {
        Player player = new Player();
        MyUser myUser = new MyUser(null, playerIDTO.getUsername(), playerIDTO.getPassword(), playerIDTO.getName(), playerIDTO.getEmail(), playerIDTO.getPhoneNumber(), "PLAYER",null,null,null);

        player.setMyUser(myUser);
        playerRepository.save(player);
        authRepository.save(myUser);
    }

    public Player getMyPlayer(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        if (user.getPlayer() == null) throw new ApiException("player not found");

        return user.getPlayer();
    }

    public void updatePlayer(Integer userId, PlayerIDTO playerIDTO) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Player oldPlayer = playerRepository.findPlayerById(userId);
        if(oldPlayer == null) throw new ApiException("player not found");

        user.setUsername(playerIDTO.getUsername());
        user.setPassword(playerIDTO.getPassword());
        user.setName(playerIDTO.getName());
        user.setEmail(playerIDTO.getEmail());
        user.setPhoneNumber(playerIDTO.getPhoneNumber());

        oldPlayer.setMyUser(user);
        playerRepository.save(oldPlayer);
    }

    public void deletePlayer(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        Player oldPlayer = playerRepository.findPlayerById(userId);
        if(oldPlayer == null) throw new ApiException("player not found");

        oldPlayer.setMyUser(null);

        playerRepository.delete(oldPlayer);
        authRepository.delete(user);
    }




}
