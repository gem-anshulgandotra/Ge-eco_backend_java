package com.gecobackend.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.gecobackend.backend.model.Authtoken;

public interface AuthTokenRepository extends MongoRepository<Authtoken,String>{

    Authtoken findByUsernameAndStatus(String userNameFromJwtToken, int i);


    void deleteAllByTokenAndStatus(String token, int i);
    
}
