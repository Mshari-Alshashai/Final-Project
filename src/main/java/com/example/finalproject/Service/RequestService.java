package com.example.finalproject.Service;

import com.example.finalproject.Model.Request;
import com.example.finalproject.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public void addRequest(Request request){
        requestRepository.save(request);
    }

}
