package com.gecobackend.backend.repository;

import com.gecobackend.backend.model.Company;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company,String> {
     Company findByCompanyName(String companyName);
    
}
