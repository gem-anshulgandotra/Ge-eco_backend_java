package com.gecobackend.backend.repository;

import com.gecobackend.backend.model.Introduction;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntroductionRepository extends MongoRepository<Introduction,String> {
    Introduction findByGemName(String gemName);
}
