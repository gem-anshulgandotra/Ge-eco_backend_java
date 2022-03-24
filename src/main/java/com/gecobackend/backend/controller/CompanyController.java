package com.gecobackend.backend.controller;

import com.gecobackend.backend.model.Company;
import com.gecobackend.backend.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CompanyController {
    
@Autowired
private CompanyService companyService;

@GetMapping(path = "/company")
public ResponseEntity<?> getCompanyList(){
    return companyService.getAllCompany();
}

@PostMapping(path = "/company/create",consumes = "application/json",produces = "application/json")
public ResponseEntity<?> createCompany(@RequestBody Company company){
    return companyService.createCompany(company);
}

}
