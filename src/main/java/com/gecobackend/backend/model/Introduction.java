package com.gecobackend.backend.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Introduction {
    
    @Indexed(unique = true)
    @Field
    public String gemName;

    @Field
    public List<Map<String,String>> use;

    @Field
    public List<Map<String,String>> why;

    public void setGemName(String gemName){
        this.gemName = gemName;
    }

    public String getGemName(){
        return this.gemName;
    }

    public void setUse(List<Map<String,String>> use){
        this.use = use;
    }

    public List<Map<String,String>> getUse(){
        return this.use;
    } 

    public void setWhy(List<Map<String,String>> why){
        this.why = why;
    }

    public List<Map<String,String>> getWhy(){
        return this.why;
    } 
}
