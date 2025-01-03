package com.example.finalproject.Service;

import com.example.finalproject.Model.Image;
import com.example.finalproject.Repository.GameRepository;
import com.example.finalproject.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final GameRepository gameRepository;

    public void uploadImage(Integer userId, Integer gameId,MultipartFile file) throws IOException {
        Image image = new Image();

        image.setGame(gameRepository.findGameById(gameId));
        image.setImageData(file.getBytes());

        imageRepository.save(image);
    }

    public byte[] getImage(Integer imageId) throws IOException {
        Optional<Image> imageOptional = imageRepository.findById(imageId);
        return imageOptional.map(Image::getImageData).orElseThrow(() -> new IOException("Image not found"));
    }
}
