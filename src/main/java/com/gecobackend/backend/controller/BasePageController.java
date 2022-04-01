package com.gecobackend.backend.controller;

import javax.validation.Valid;

import com.gecobackend.backend.model.BasePage;
import com.gecobackend.backend.service.BasePageService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BasePageController {
    
    private final BasePageService basePageService;
    public BasePageController(BasePageService basePageService){
        this.basePageService = basePageService;
    }

    @GetMapping(path = "/base", produces = "application/json")
    public ResponseEntity<?> getBasePageData(){
        return basePageService.getBasePageDetails();
    }

    @PostMapping(path = "/base", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> setBasePageDetail(@RequestBody BasePage basePageData){
        return basePageService.setBasePageDetail(basePageData);
    }

    @DeleteMapping(path = "/base", produces = "application/json")
    public ResponseEntity<?> deleteBasePageDetail(@RequestParam String title){
        return basePageService.deleteBasePageDetail(title);
    }
}
