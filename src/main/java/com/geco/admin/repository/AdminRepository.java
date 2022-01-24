package com.geco.admin.repository;

import com.geco.admin.model.Admin;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin,String> {

    Admin findByEmailAndPassword(String email, String pass);

    Admin findByEmail(String username);
    
}
