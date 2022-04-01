package com.gecobackend.backend.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

class Fields {
    public String title;
    public String img;
    public String content;
    public List<Fields> innerFields;
}

@Document
public class BasePage {

    @Indexed(unique = true)
    @Field
    public String title;

    @Field
    public String subTitle;

    @Field
    public List<Fields> fields;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<Fields> getFields() {
        return this.fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

}
