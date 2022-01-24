package com.geco.admin.service;

import java.util.List;

import com.geco.admin.helper.JwtHelper;
import com.geco.admin.model.Admin;
import com.geco.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

@Service
public class AdminService {
    
    @Autowired
   private AdminRepository admin;


   @Autowired
   private JwtHelper jwtHelper;




// @Autowired
// private JwtHelper jwtHelper;


   public void saveUser(Admin user){
       user.setPassword(EncryptePassword(user.getPassword()));
    admin.save(user);
   }
    
   public List<Admin> getUser(){
  return admin.findAll();
 }

 public ResponseEntity<?> loginUser(Admin principal){

    String pass=EncryptePassword(principal.getPassword());
    String email=principal.getEmail();
    if(admin.findByEmailAndPassword(email, pass)!=null){
//         String jwtToken=Jwts.builder().claim("email", email).claim("password",pass).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5))).setId(UUID.randomUUID().toString()).compact();
// return  jwtToken;

    return ResponseEntity.ok(jwtHelper.generateToken(email,pass));

    }else{
    return ResponseEntity.badRequest().body("Bad Credentials");
    }
 }



 public String EncryptePassword(String password){
    String md5Hex = DigestUtils.md5Hex(password);
     return md5Hex;
 }

public String checkUser(String id) {
    return "Welcome";
}
}
