package com.gecobackend.backend.service;

import java.util.List;

import com.gecobackend.backend.entity.Response;
import com.gecobackend.backend.model.BasePage;
import com.gecobackend.backend.repository.BasePageRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BasePageService {
    private BasePageRepository basePageRepository;
    public BasePageService(BasePageRepository basePageRepository){
        this.basePageRepository = basePageRepository;
    }

    public ResponseEntity<?> getBasePageDetails(){
        Response resp = new Response();
        List<BasePage> basePageData = basePageRepository.findAll();
        if(basePageData != null){
            resp.setData(basePageData);
            resp.setMessage("Data fetched successfully");
            resp.setOperation("Success");
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }
        else{
            resp.setMessage("No data exist");
            resp.setOperation("Failure");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }
    }

    public ResponseEntity<?> setBasePageDetail(BasePage basePageData){
        Response resp = new Response();
        if(basePageData != null){
            System.out.println(basePageRepository.save(basePageData));
            resp.setOperation("Success");
            resp.setMessage("Data inserted Successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
        }
        else{
            resp.setOperation("Failure");
            resp.setMessage("No Data to insert");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    public ResponseEntity<?> deleteBasePageDetail(String title){
        Response resp = new Response();
        if(title.isEmpty() || title == null){
            resp.setOperation("Failure");
            resp.setMessage("No key present in requerst");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
        else{
            if(basePageRepository.deleteByTitle(title) == null){
                resp.setMessage("Title - " + title + " not exist");
                resp.setOperation("Failure");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
            }
            else{
                resp.setMessage("Data deleted Successfully");
                resp.setOperation("Success");
                return ResponseEntity.status(HttpStatus.OK).body(resp);
            }
            
        }
    }
    
}
