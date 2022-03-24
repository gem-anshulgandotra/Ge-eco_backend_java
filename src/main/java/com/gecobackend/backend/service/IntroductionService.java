package com.gecobackend.backend.service;

import java.util.List;

import com.gecobackend.backend.entity.Response;
import com.gecobackend.backend.model.Introduction;
import com.gecobackend.backend.repository.IntroductionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IntroductionService {
    private final IntroductionRepository introductionRepository;

    public IntroductionService(IntroductionRepository introductionRepository) {
        this.introductionRepository = introductionRepository;
    }
    public ResponseEntity<?> introDetailsForGem(String gemName){
        Response resp = new Response();
        Introduction gemIntro = introductionRepository.findByGemName(gemName);
        resp.setData(gemIntro);
        if(gemIntro == null){
            resp.setMessage("No data exist for " + gemName );
            resp.setOperation("Failure");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }
        else{
            resp.setMessage(gemName + " Introduction fetched successfully");
            resp.setOperation("Success");
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
    
    }
    public ResponseEntity<?> introDetails(){
        Response resp = new Response();
        List<Introduction> gemIntro = introductionRepository.findAll();
        resp.setData(gemIntro);
        if(gemIntro.isEmpty() || gemIntro == null){
            resp.setMessage("Introduction details not exists");
            resp.setOperation("Failure");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }
        else{
            resp.setMessage("Introduction details fetched successfully");
            resp.setOperation("Success");
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
        
    }

    public ResponseEntity<?> setGemIntro(Introduction gemIntro){
        System.out.println(gemIntro.getWhy() + " " + gemIntro.getUse());
        introductionRepository.save(gemIntro);
        Response resp = new Response();
        resp.setData("");
        resp.setMessage("Data Inserted");
        resp.setOperation("Success");
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

}
