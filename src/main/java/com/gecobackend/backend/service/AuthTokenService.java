package com.gecobackend.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.gecobackend.backend.entity.JwtHelper;
import com.gecobackend.backend.entity.Response;
import com.gecobackend.backend.model.Authtoken;
import com.gecobackend.backend.repository.AuthTokenRepository;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService{

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    MongoOperations mongoOperation;

    @Autowired
    private JwtHelper jwtHelper;

    public ResponseEntity<?> getBridgeToken(HttpServletRequest request) {
        Response res=new Response();
        res.setOperation("Success");
        res.setMessage("Token Fetched");
        Map<String,String> data=new HashMap<String,String>();
        String token=request.getHeader("Authorization");
        Authtoken currentToken= authTokenRepository.findByUsernameAndStatus(jwtHelper.getUserNameFromJwtToken(token.substring(7)),1);
       
        data.put("bridgeToken",currentToken.getBridgeToken());
        res.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    public String postBridgeToken(String username) {
    Authtoken authtoken = new Authtoken();
    Response resp=new Response();
    authtoken.setUsername(username);    
    authtoken.setStatus(1);
    Date date = new Date();
    long unixTime = date.getTime();
    authtoken.setinsertTime(unixTime);
    UUID identifier=UUID.randomUUID();
   
    authtoken.setBridgeToken(identifier.toString()+unixTime);
    resp.setData(authtoken);
    authTokenRepository.save(authtoken);
        
        return authtoken.getBridgeToken();
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
        StringBuilder username=new StringBuilder(jwtHelper.getUserNameFromJwtToken(token.substring(7)));
       Authtoken newToken=new Authtoken();
       newToken.setStatus(1);
       newToken.setUsername(username.toString());
       Date date = new Date();
       long unixTime = date.getTime();
       newToken.setinsertTime(unixTime);
       UUID identifier=UUID.randomUUID();

       newToken.setBridgeToken(identifier.toString()+unixTime);
        authTokenRepository.save(newToken);
        data.put("bridgeToken",newToken.getBridgeToken());
        res.setData(data);
	    Query query = new Query();
	    query.addCriteria(Criteria.where("username").is(username.toString()));
        query.addCriteria(Criteria.where("status").is(1));
        Update updation = new Update();
		updation.set("status", 0);
        mongoOperation.findAndModify(query, updation, new FindAndModifyOptions().returnNew(true), Authtoken.class);
        return ResponseEntity.status(HttpStatus.OK).body(res);



    }
    
}
