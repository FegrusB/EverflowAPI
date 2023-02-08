package com.example.everflowapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpidCSVService {

    @Autowired
    SpidRepository repository;

    public void save(MultipartFile file){
        try{
            List<Spid> spids = SpidCSVHelper.csvToSpid(file.getInputStream());
            repository.saveAll(spids);
        } catch (IOException e){
            throw new RuntimeException("Failed to store data" + e.getMessage());
        }
    }
    public List<Spid> getAllSpids(){
        return (List<Spid>) repository.findAll();
    }
}
