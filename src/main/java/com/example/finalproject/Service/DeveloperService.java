package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.DTO.DeveloperIDTO;
import com.example.finalproject.DTO.DeveloperODTO;
import com.example.finalproject.DTO.GameODTO;
import com.example.finalproject.DTO.PlayerIDTO;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.DeveloperRepository;
import com.example.finalproject.Repository.GameRepository;
import com.example.finalproject.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final AuthRepository authRepository;
    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final ReviewRepository reviewRepository;

    //Get developer by id
    public Developer getMyDeveloper(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) throw new ApiException("user not found");
        if (user.getDeveloper() == null) throw new ApiException("developer not found");

        return user.getDeveloper();
    }

    public void register(DeveloperIDTO developerIDTO) {
        Developer developer = convertDeveloperDTOToDeveloper(developerIDTO);
        developerRepository.save(developer);
    }

    public void updateDeveloper(Integer userId, DeveloperIDTO developerIDTO) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) throw new ApiException("user not found");

        Developer oldDeveloper = developerRepository.findDeveloperById(userId);
        if (oldDeveloper == null) throw new ApiException("developer not found");

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
        if (user == null) throw new ApiException("user not found");
        Developer oldDeveloper = developerRepository.findDeveloperById(userId);
        if (oldDeveloper == null) throw new ApiException("developer not found");

        oldDeveloper.setMyUser(null);

        developerRepository.delete(oldDeveloper);
        authRepository.delete(user);
    }
//
//
//    public List<DeveloperODTO> getAllMyGames(Integer userId) {
//        MyUser user = authRepository.findMyUserById(userId);
//        if (user == null) throw new ApiException("user not found");
//
//        Developer developer = developerRepository.findDeveloperById(user.getId());
//        if (developer == null) throw new ApiException("developer not found");
//
//        List<GameODTO> gameODTOS = new ArrayList<>();
//        for (Game game : developer.getGames()) {
//            GameODTO gameODTO = new GameODTO();
//            gameODTO.setName(game.getName());
//            gameODTO.setPrice(game.getPrice());
//            gameODTO.setSize(game.getSize());
//            gameODTOS.add(gameODTO);
//        }
//
//        DeveloperODTO developerODTO = new DeveloperODTO();
//        developerODTO.setGames(gameODTOS);
//
//        List<DeveloperODTO> developerODTOS = new ArrayList<>();
//        developerODTOS.add(developerODTO);
//
//        return developerODTOS;
//    }
//
//    public DeveloperODTO searchMyGame(Integer userId, Integer gameId) {
//        MyUser user = authRepository.findMyUserById(userId);
//        if (user == null) {
//            throw new ApiException("User not found");
//        }
//
//        Developer developer = developerRepository.findDeveloperById(user.getId());
//        if (developer == null) {
//            throw new ApiException("Developer not found");
//        }
//
//        Game game = gameRepository.findGameByIdAndDeveloperId(gameId, developer.getId());
//        if (game == null) {
//            throw new ApiException("Game not found");
//        }
//
//        GameODTO gameODTO = new GameODTO();
//        gameODTO.setName(game.getName());
//        gameODTO.setPrice(game.getPrice());
//        gameODTO.setSize(game.getSize());
//
//        DeveloperODTO developerODTO = new DeveloperODTO();
//        developerODTO.setGames(Collections.singletonList(gameODTO));
//
//        return developerODTO;
//    }
//
//    public double getAverageRatingForGame(Integer developerId, Integer gameId) {
//
//        Developer developer = developerRepository.findDeveloperById(developerId);
//        if (developer == null) {
//            throw new ApiException("Developer not found");
//        }
//
//
//        Game targetGame = null;
//        Set<Game> developerGames = developer.getGames();
//        if (developerGames != null) {
//            for (Game game : developerGames) {
//                if (game != null && game.getId().equals(gameId)) {
//                    targetGame = game;
//                    break;
//                }
//            }
//        }
//
//        if (targetGame == null) {
//            throw new ApiException("Game not found or does not belong to this developer");
//        }
//
//
//        List<Review> reviews = reviewRepository.findByGameId(gameId);
//        if (reviews == null || reviews.isEmpty()) {
//            throw new ApiException("review is empty");
//        }
//
//        Integer totalRating = 0;
//        Integer reviewCount = 0;
//
//        for (Review review : reviews) {
//            if (review != null) {
//                totalRating += review.getRating();
//                reviewCount++;
//            }
//        }
//        if (reviewCount == 0) {
//            return 0.0;
//        }
//        return (double) totalRating / reviewCount;
//    }



    public List<DeveloperODTO> getAllMyGames(Integer userId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) throw new ApiException("user not found");

        Developer developer = developerRepository.findDeveloperById(user.getId());
        if (developer == null) throw new ApiException("developer not found");

        List<GameODTO> gameODTOS = new ArrayList<>();
        for (Game game : developer.getGames()) {
            GameODTO gameODTO = new GameODTO();
            gameODTO.setName(game.getName());
            gameODTO.setPrice(game.getPrice());
            gameODTO.setSize(game.getSize());
            gameODTOS.add(gameODTO);
        }

        DeveloperODTO developerODTO = new DeveloperODTO();
        developerODTO.setGames(gameODTOS);

        List<DeveloperODTO> developerODTOS = new ArrayList<>();
        developerODTOS.add(developerODTO);

        return developerODTOS;
    }

    public DeveloperODTO searchMyGame(Integer userId, Integer gameId) {
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }

        Developer developer = developerRepository.findDeveloperById(user.getId());
        if (developer == null) {
            throw new ApiException("Developer not found");
        }

        Game game = gameRepository.findGameByIdAndDeveloperId(gameId, developer.getId());
        if (game == null) {
            throw new ApiException("Game not found");
        }

        GameODTO gameODTO = new GameODTO();
        gameODTO.setName(game.getName());
        gameODTO.setPrice(game.getPrice());
        gameODTO.setSize(game.getSize());

        DeveloperODTO developerODTO = new DeveloperODTO();
        developerODTO.setGames(Collections.singletonList(gameODTO));

        return developerODTO;
    }

    public double getAverageRatingForGame(Integer developerId, Integer gameId) {

        Developer developer = developerRepository.findDeveloperById(developerId);
        if (developer == null) {
            throw new ApiException("Developer not found");
        }


        Game targetGame = null;
        Set<Game> developerGames = developer.getGames();
        if (developerGames != null) {
            for (Game game : developerGames) {
                if (game != null && game.getId().equals(gameId)) {
                    targetGame = game;
                    break;
                }
            }
        }

        if (targetGame == null) {
            throw new ApiException("Game not found or does not belong to this developer");
        }


        List<Review> reviews = reviewRepository.findByGameId(gameId);
        if (reviews == null || reviews.isEmpty()) {
            throw new ApiException("review is empty");
        }

        int totalRating = 0, reviewCount = 0;


        for (Review review : reviews) {
            if (review != null) {
                totalRating += review.getRating();
                reviewCount++;
            }
        }
        if (reviewCount == 0) {
            return 0.0;
        }
        return (double) totalRating / reviewCount;
    }
      
    public Developer convertDeveloperDTOToDeveloper(DeveloperIDTO developerIDTO) {
        Developer developer = new Developer();
        MyUser myUser = new MyUser(null, developerIDTO.getUsername(), developerIDTO.getPassword(), developerIDTO.getName()
                , developerIDTO.getEmail(), developerIDTO.getPhoneNumber(), "DEVELOPER",null,null,null);
        developer.setMyUser(myUser);

        authRepository.save(myUser);

        return developer;
    }

}
