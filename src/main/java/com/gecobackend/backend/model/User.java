package com.gecobackend.backend.model;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class User {
    
    // @Id //For Primary Key
    // public int id;
    
    @Indexed(unique = true)
    public String username;

    @Field //for key
    public String firstName ;

    @Field //for key
    public String lastName;

    @Field //for key
    public String email;

    @Field //for key
    public String password;

    @Field //for key
    public String company;

    @Field //for key
    public long insertTime;

    @Field //for key
    public long updateTime;
    
    @Field //for key
    public String status;

    @Field //for key
    public String companyType;

    @Field //for key
    public long verifiedTime;

    @Field //for key
    public long selfVerifiedTime;


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public long getInsertTime() {
        return this.insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public long getVerifiedTime() {
        return this.verifiedTime;
    }

    public void setVerifiedTime(long verifiedTime) {
        this.verifiedTime = verifiedTime;
    }

    public long getSelfVerifiedTime() {
        return this.selfVerifiedTime;
    }

    public void setSelfVerifiedTime(long selfVerifiedTime) {
        this.selfVerifiedTime = selfVerifiedTime;
    }
    




}
