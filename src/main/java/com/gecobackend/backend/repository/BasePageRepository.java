package com.gecobackend.backend.repository;

import com.gecobackend.backend.model.BasePage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePageRepository extends MongoRepository<BasePage,String>{
    BasePage findByTitle(String title);
    BasePage deleteByTitle(String title);
}