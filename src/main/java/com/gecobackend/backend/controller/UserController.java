package com.gecobackend.backend.controller;


import javax.servlet.http.HttpServletRequest;

import com.gecobackend.backend.model.User;
import com.gecobackend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class UserController extends RuntimeException {
    
    
   @Autowired
   public UserService userService;
   

     @PostMapping(path="/user",consumes = "application/json",produces = "application/json") //PostApi
        public ResponseEntity<?> createUser(@RequestBody User userDetails,HttpServletRequest request){ 
            return userService.createUser(userDetails,request);
        }


        @GetMapping(path="/validate/username",produces = "application/json")
        public ResponseEntity<?> validate(@RequestParam String username) {   
            return  userService.validateUser(username);
        }

        @PostMapping(path="/login")
        public ResponseEntity<?> loginUser(@RequestBody User credentials) {

            return userService.loginUser(credentials);
        }
        

    }
