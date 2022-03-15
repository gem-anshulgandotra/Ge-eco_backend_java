package com.gecobackend.backend.controller;

import javax.validation.constraints.Null;

import com.gecobackend.backend.model.Introduction;
import com.gecobackend.backend.service.IntroductionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntroductionController {

    private final IntroductionService introService;
    

    public IntroductionController(IntroductionService introService) {
        this.introService = introService;
    }

    // @GetMapping(path = "/intro", produces = "application/json")
    // public ResponseEntity<?> getIntro(){
    //     return introService.introDetails();
    // }

    @GetMapping(path = "/intro", produces = "application/json")
    public ResponseEntity<?> getGemIntro(@RequestParam(name = "gem",defaultValue = "") String gemName){
        if(gemName.isEmpty()){
            return introService.introDetails();
        }
        return introService.introDetailsForGem(gemName);
    }

    @PostMapping(path = "/intro", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> setGemIntro(@RequestBody Introduction gemIntro){
        return introService.setGemIntro(gemIntro);
    }
}
