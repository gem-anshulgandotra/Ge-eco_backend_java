package com.gecobackend.backend.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Authtoken {



    @Field
    private String username;
    @Field
    private String token;
    @Field
    private Date inserTime;
    @Field
    private int status;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Date getInserTime() {
        return inserTime;
    }
    public void setInserTime(Date inserTime) {
        this.inserTime = inserTime;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
