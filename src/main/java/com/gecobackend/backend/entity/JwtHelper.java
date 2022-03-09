package com.gecobackend.backend.entity;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.gecobackend.backend.model.User;


@Service
public class JwtHelper {


    public User user=new User();
    
    private String SECRET_KEY = "secret";


    public String generateToken(String email, String password) {
        return createToken(email,password);
    }

    private String createToken(String email, String password) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    } 
    
    }
