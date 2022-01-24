package com.geco.admin.controller;

import java.util.List;

import com.geco.admin.model.Admin;
import com.geco.admin.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {



    @Autowired
    private AdminService adminService;


   @PostMapping("/create/user")
   public ResponseEntity<?> saveUser(@RequestBody Admin user){
   adminService.saveUser(user);
   return ResponseEntity.ok("User Created");
   } 

   @GetMapping("/get")
   public List<Admin> getUser(){
    return adminService.getUser();
   }


   @GetMapping("/login")
   public ResponseEntity<?> loginUser(@RequestBody Admin user){
       return adminService.loginUser(user);
   }


   @GetMapping("/user")
   public String checkUser(@RequestParam String id){
   return adminService.checkUser(id);
   }
}
