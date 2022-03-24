package com.gecobackend.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gecobackend.backend.entity.Response;
import com.gecobackend.backend.model.Company;
import com.gecobackend.backend.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    
    @Autowired // Alternative for cinding using constructor
    private CompanyRepository companyRepository;

    public ResponseEntity<?> createCompany(Company company) {
        Date date = new Date();
        long unixTime = date.getTime();
        company.setInsertTime(unixTime);
        companyRepository.save(company);
        return ResponseEntity.ok("Company Registered");

    }

    public ResponseEntity<?> getAllCompany() {

    

        Response resp=new Response();
        List<Company> companyList= companyRepository.findAll();
        List<String> companies=new ArrayList<String>();

        for(int i=0; i<companyList.size(); i++){
            companies.add(companyList.get(i).getCompanyName().toLowerCase());
        }
        resp.setData(companies);
        resp.setMessage(companies.size()+ " Records Found");
        resp.setOperation("Success");
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

}
