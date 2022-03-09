package com.gecobackend.backend.controller;

import javax.servlet.http.HttpServletRequest;
import com.gecobackend.backend.service.AuthTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthTokenController {

    @Autowired
    private AuthTokenService authTokenService;
    
@GetMapping(path="/bridge/token")
public ResponseEntity<?> getBridgeToken(HttpServletRequest request){
    return authTokenService.getBridgeToken(request);
    
}
// @PostMapping(path="/bridge/token/create",produces = "application/json",consumes ="application/json")
// public ResponseEntity<?> postBridgeToken(@RequestBody Authtoken authtoken){
//     System.out.println("---------"+authtoken.getUsername());
//     return authTokenService.postBridgeToken(authtoken);
// }
@PostMapping(path="/bridge/token/change",produces = "application/json")
public ResponseEntity<?> changeBridgeToken(HttpServletRequest request){
    return authTokenService.changeBridgeToken(request);
}

}
