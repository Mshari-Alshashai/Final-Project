package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload/{userId}/{gameId}")
    public ResponseEntity<ApiResponse> uploadImage(@PathVariable Integer userId, @PathVariable Integer gameId, @RequestParam MultipartFile file) {
                try {
                    imageService.uploadImage(userId, gameId, file);
                    return ResponseEntity.status(200).body(new ApiResponse("Successfully uploaded image"));
                }
                catch (IOException e) {
                    return ResponseEntity.status(400).body(new ApiResponse("Failed to upload image"));
                }
    }

    @GetMapping("/get-image/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer imageId) throws IOException {
        byte[] imageData = imageService.getImage(imageId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/jpeg");
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
