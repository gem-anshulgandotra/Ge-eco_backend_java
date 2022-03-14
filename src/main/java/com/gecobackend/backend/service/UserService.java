package com.gecobackend.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gecobackend.backend.entity.JwtHelper;
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

   @Autowired
   private JwtHelper jwtHelper;

   @Autowired
   private AuthTokenService authTokenService;


    public ResponseEntity<?> createUser(User userDetails,HttpServletRequest request){
        Response resp=new Response();
       
        try{
            if(userDetails.getUsername()==null || userDetails.getFirstName()==null
            || userDetails.getLastName()==null||userDetails.getEmail()==null || userDetails.getPassword()==null
            || userDetails.getCompany()==null){
                throw new CompulsaryFieldException();
            }

            userDetails.setUsername(userDetails.getUsername().toLowerCase());


    Company company = companyRepository.findByCompanyName(userDetails.getCompany());
    if(company != null && company.getCompanyName().equals(userDetails.getCompany()) ){
       
        userDetails.setCompanyType("Existing");

    }else{
        userDetails.setCompanyType("New");
        Company newCompany = new Company();
        newCompany.setCompanyName(userDetails.getCompany());
        Date date = new Date();
        long unixTime = date.getTime();
        newCompany.setInsertTime(unixTime);
        newCompany.setCompanyType("User");
        companyRepository.save(newCompany);
        
    }
    Date date = new Date();
    long unixTime = date.getTime();
        userDetails.setInsertTime(unixTime);
        userDetails.setStatus("New");
        userDetails.setPassword(EncryptePassword(userDetails.getPassword()));
        userRepository.save(userDetails);
        resp.setOperation("success");
        resp.setMessage("User created successfully");
        String token=authTokenService.postBridgeToken(userDetails.getUsername());
Map<String,String> data=new HashMap<String,String>();
data.put("bridgeToken",token);
        // resp.setData(userDetails);
        resp.setData(data);
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
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resp);
        }
       
        catch (Exception e){
            
            resp.setOperation("Error");
            resp.setMessage("Something Went Wrong!");
            resp.setData(null); 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
     
   
        
    }

    public ResponseEntity<?> validateUser(String username){

Response resp=new Response();
try{
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

  

    public ResponseEntity<?> loginUser(User credentials) {
        Response resp=new Response();
     String pass=EncryptePassword(credentials.getPassword());
     String username=credentials.getUsername().toLowerCase();

     if(userRepository.findByUsernameAndPassword(username, pass)!=null){
        resp.setOperation("Success");
        resp.setMessage("Login Success");
        Map<String,String> data=new HashMap<String,String>();
        data.put("token",jwtHelper.generateToken(username,pass));
        resp.setData(data);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        
            }else{
          
                resp.setOperation("Error");
                resp.setMessage("Login Failed");
                Map<String,String> data=new HashMap<String,String>();
                data.put("token","Login Failed");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

            }

    }
    






    public String EncryptePassword(String password){
        String md5Hex = DigestUtils.md5Hex(password);
         return md5Hex;
     }
}
