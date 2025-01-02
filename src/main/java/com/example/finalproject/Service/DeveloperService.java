package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.DeveloperIDTO;
import com.example.finalproject.DTO.PlayerIDTO;
import com.example.finalproject.Model.Developer;
import com.example.finalproject.Model.MyUser;
import com.example.finalproject.Model.Player;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;
    private final AuthRepository authRepository;

    //Get developer by id
    public Developer getMyDeveloper(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        if (user.getDeveloper() == null) throw new ApiException("developer not found");

        return user.getDeveloper();
    }

    public void register(DeveloperIDTO developerIDTO) {
        Developer developer = new Developer();
        MyUser myUser = new MyUser(null, developerIDTO.getUsername(), developerIDTO.getPassword(), developerIDTO.getName(), developerIDTO.getEmail(), developerIDTO.getPhoneNumber(), "DEVELOPER",null,null,null);

        developer.setMyUser(myUser);
        developerRepository.save(developer);
        authRepository.save(myUser);
    }

    public void updateDeveloper(Integer userId, DeveloperIDTO developerIDTO) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");

        Developer oldDeveloper = developerRepository.findDeveloperById(userId);
        if(oldDeveloper == null) throw new ApiException("developer not found");

        user.setUsername(developerIDTO.getUsername());
        user.setPassword(developerIDTO.getPassword());
        user.setName(developerIDTO.getName());
        user.setEmail(developerIDTO.getEmail());
        user.setPhoneNumber(developerIDTO.getPhoneNumber());
        oldDeveloper.setBio(developerIDTO.getBio());
        oldDeveloper.setMyUser(user);

        developerRepository.save(oldDeveloper);


    }

    public void deleteDeveloper(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if(user == null) throw new ApiException("user not found");
        Developer oldDeveloper = developerRepository.findDeveloperById(userId);
        if(oldDeveloper == null) throw new ApiException("developer not found");

        oldDeveloper.setMyUser(null);

        developerRepository.delete(oldDeveloper);
        authRepository.delete(user);
    }

}
