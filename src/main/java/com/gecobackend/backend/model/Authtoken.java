package com.gecobackend.backend.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Authtoken {



    @Field
    private String username;
    @Field
    private String bridgeToken;
    @Field
    private long insertTime;
    @Field
    private int status;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getBridgeToken() {
        return bridgeToken;
    }
    public void setBridgeToken(String token) {
        this.bridgeToken = token;
    }
    public long getinsertTime() {
        return insertTime;
    }
    public void setinsertTime(long insertTime) {
        this.insertTime = insertTime;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
