package com.gecobackend.backend.repository;

import com.gecobackend.backend.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Integer>{
    
User findByUsername(String username);

User findByUsernameAndPassword(String username, String pass);

}
