package com.gecobackend.backend.controller;


import com.gecobackend.backend.model.User;
import com.gecobackend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends RuntimeException {
    
    
   @Autowired
   public UserService userService;
   


    @PostMapping(path="/user",consumes = "application/json",produces = "application/json") //PostApi
    public ResponseEntity<?> createUser(@RequestBody User userDetails){ 
        return userService.createUser(userDetails);
        }


        @GetMapping(path="/validate/username",produces = "application/json")
        public ResponseEntity<?> validate(@RequestParam String username)
        {
           
return  userService.validateUser(username);
        }
    }
