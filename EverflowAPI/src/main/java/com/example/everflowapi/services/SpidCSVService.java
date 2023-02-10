package com.example.everflowapi.services;

import com.example.everflowapi.helpers.CSVHelper;
import com.example.everflowapi.models.Spid;
import com.example.everflowapi.repositories.SpidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class SpidCSVService implements CSVServiceable {

    @Autowired
    SpidRepository repository;

    CSVHelper.CSVResult result;

    public int[] save(MultipartFile file,CSVHelper.CSVResult result){
        try{
            result = CSVHelper.csvToSpid(file.getInputStream());
            for (Object reading: result.getData()) {
                try {repository.save((Spid) reading);
                } catch (Exception e){result.addFailure();}
            }
        } catch (IOException e){
            throw new RuntimeException("Failed to store data" + e.getMessage());
        }
        return new int[] {result.getNumSuccess(), result.getNumMissed()};
    }

}
