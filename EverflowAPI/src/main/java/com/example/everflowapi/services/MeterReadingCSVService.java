package com.example.everflowapi.services;

import com.example.everflowapi.helpers.CSVHelper;
import com.example.everflowapi.models.MeterReading;
import com.example.everflowapi.repositories.MeterReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MeterReadingCSVService implements CSVServiceable {

    @Autowired
    MeterReadingRepository repository;

    public int[] save(MultipartFile file, CSVHelper.CSVResult result){
        try{
            result = CSVHelper.csvToMeterReading(file.getInputStream());
            repository.saveAll(result.getData());
        } catch (IOException e){
            throw new RuntimeException("Failed to store data" + e.getMessage());
        }
        return new int[] {result.getNumSuccess(),result.getNumMissed()};
    }
    public List<MeterReading> getAllMeterReadings(){
        return (List<MeterReading>) repository.findAll();
    }
}

