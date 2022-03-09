package com.gecobackend.backend.service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gecobackend.backend.entity.JwtHelper;
import com.gecobackend.backend.entity.Response;
import com.gecobackend.backend.model.Authtoken;
import com.gecobackend.backend.repository.AuthTokenRepository;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private JwtHelper jwtHelper;

    public ResponseEntity<?> getBridgeToken(HttpServletRequest request) {
        Response res=new Response();
        res.setOperation("Success");
        res.setMessage("Token Fetched");
        Map<String,String> data=new HashMap<String,String>();
        String token=request.getHeader("Authorization");
        System.out.println(jwtHelper.getUserNameFromJwtToken(token.substring(7)));
        Authtoken currentToken= authTokenRepository.findByUsernameAndStatus(jwtHelper.getUserNameFromJwtToken(token.substring(7)),1);
        data.put("token",currentToken.getToken());
        res.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    public String postBridgeToken(String username) {
    Authtoken authtoken = new Authtoken();
    Response resp=new Response();
    authtoken.setUsername(username);    
    authtoken.setStatus(1);
    authtoken.setInserTime(new Date());
    authtoken.setToken(EncryptePassword(authtoken.getUsername()+Instant.now().toEpochMilli()));
    resp.setData(authtoken);
    authTokenRepository.save(authtoken);
        
        return authtoken.getToken();
    }


    public String EncryptePassword(String password){
        String md5Hex = DigestUtils.md5Hex(password);
         return md5Hex;
     }

    public ResponseEntity<?> changeBridgeToken(HttpServletRequest request) {
        Response res=new Response();
        res.setOperation("Success");
        res.setMessage("Token Changed");
        Map<String,String> data=new HashMap<String,String>();
        StringBuilder token=new StringBuilder(request.getHeader("Authorization"));
        Authtoken currentToken= authTokenRepository.findByUsernameAndStatus(jwtHelper.getUserNameFromJwtToken(token.substring(7)),1);
        currentToken.setStatus(0);
       currentToken.setToken(currentToken.getToken());
       currentToken.setUsername(currentToken.getUsername());
       currentToken.setInserTime(currentToken.getInserTime());
       authTokenRepository.save(currentToken);
       authTokenRepository.deleteAllByTokenAndStatus(currentToken.getToken(),1);
       Authtoken newToken=new Authtoken();
       newToken.setStatus(1);
       newToken.setUsername(jwtHelper.getUserNameFromJwtToken(token.substring(7)));
       newToken.setInserTime(new Date());
       newToken.setToken(EncryptePassword(jwtHelper.getUserNameFromJwtToken(token.substring(7))+Instant.now().toEpochMilli()));
        authTokenRepository.save(newToken);
        data.put("token",newToken.getToken());
        res.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    
}
