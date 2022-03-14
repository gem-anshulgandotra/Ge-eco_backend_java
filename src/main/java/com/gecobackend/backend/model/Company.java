package com.gecobackend.backend.model;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Company {

    @Indexed(unique = true)
    @Field
    public String companyName;

    @Field
    public String companyType="System";

    @Field
    public long insertTime;

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public long getInsertTime() {
        return this.insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

}
