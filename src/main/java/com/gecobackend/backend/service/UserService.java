package com.gecobackend.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.gecobackend.backend.entity.Response;
import com.gecobackend.backend.exceptions.CompulsaryFieldException;
import com.gecobackend.backend.model.Company;
import com.gecobackend.backend.model.User;
import com.gecobackend.backend.repository.CompanyRepository;
import com.gecobackend.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
@Service
public class UserService {
    
@Autowired
private UserRepository userRepository;

@Autowired
private CompanyRepository companyRepository;


    public ResponseEntity<?> createUser(User userDetails){
        userDetails.setUsername(userDetails.getUsername().toLowerCase());
        Response resp=new Response();
       
        try{
            if(userDetails.getUsername()==null || userDetails.getFirstName()==null
            || userDetails.getLastName()==null||userDetails.getEmail()==null || userDetails.getPassword()==null
            || userDetails.getCompany()==null){
                throw new CompulsaryFieldException();
            }

    //  List<Company> li=companyRepository.findAll();
    //    for(int i=0; i<li.size(); i++){
    //        System.out.println(li.get(i).getCompanyName());
    //    }

    Company company = companyRepository.findByCompanyName(userDetails.getCompany());
    if(company != null && company.getCompanyName().equals(userDetails.getCompany()) ){
       
        userDetails.setCompanyType("Existing");

    }else{
        userDetails.setCompanyType("New");
        Company newCompany = new Company();
        newCompany.setCompanyName(userDetails.getCompany());
        newCompany.setInsertTime(new Date());
        newCompany.setCompanyType("User");
        companyRepository.save(newCompany);
    }

        userDetails.setInsertTime(new Date());
        userDetails.setStatus("New");
        userDetails.setPassword(EncryptePassword(userDetails.getPassword()));
        userRepository.save(userDetails);
        resp.setOperation("success");
        resp.setMessage("User created successfully");
        // resp.setData(userDetails);
        resp.setData(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);

        
        } catch(CompulsaryFieldException e){

            resp.setOperation("Error");
            resp.setMessage("Compulsory Field Exception");
            resp.setData(null); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
           catch (org.springframework.dao.DuplicateKeyException e){
           
            resp.setOperation("Error");
            resp.setMessage("Username already exists");
            resp.setData(null); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
       
        catch (Exception e){
            
            System.out.println(e);
            resp.setOperation("Error");
            resp.setMessage("Something Went Wrong!");
            resp.setData(null); 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
     
   
        
    }

    public ResponseEntity<?> validateUser(String username){

Response resp=new Response();
try{
System.out.println(userRepository.findByUsername(username).getUsername() + " "+ username);
        if(userRepository.findByUsername(username).getUsername().equals(username)){
            Map<String,Boolean> data=new HashMap<String,Boolean>();
            data.put("isPresent",userRepository.findByUsername(username).getUsername().equals(username));
resp.setData(data);
resp.setMessage("User Exists");
resp.setOperation("Success");

        }else{
            throw new NullPointerException();
           
        }
       
    }catch(NullPointerException e){
        Map<String,Boolean> data=new HashMap<String,Boolean>();
        data.put("isPresent",false);
resp.setData(data);
resp.setMessage("User Does Not Exists");
resp.setOperation("Success");
    }
    return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    public String EncryptePassword(String password){
        String md5Hex = DigestUtils.md5Hex(password);
         return md5Hex;
     }
    
}
