package com.geco.admin.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.geco.admin.model.Admin;

@Service
public class JwtHelper {


    public Admin admin=new Admin();
    
    private String SECRET_KEY = "secret";


    public String generateToken(String email, String password) {
        return createToken(email,password);
    }

    private String createToken(String email, String password) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    } 
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e) {
            System.out.println("Invalid JWT token: {}"+ e.getMessage());
        } catch (MalformedJwtException e) {
           System.out.println("Invalid JWT token: {}"+e.getMessage());
        } catch (ExpiredJwtException e) {
           System.out.println("JWT token is expired: {}"+ e.getMessage());
        } catch (UnsupportedJwtException e) {
           System.out.println("JWT token is unsupported: {}"+ e.getMessage());
        } catch (IllegalArgumentException e) {
          
        }
return false;
      
    }
    }
