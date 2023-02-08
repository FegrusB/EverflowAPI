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

    public void save(MultipartFile file){
        try{
            List<MeterReading> meterReadings = CSVHelper.csvToMeterReading(file.getInputStream());
            repository.saveAll(meterReadings);
        } catch (IOException e){
            throw new RuntimeException("Failed to store data" + e.getMessage());
        }
    }
    public List<MeterReading> getAllMeterReadings(){
        return (List<MeterReading>) repository.findAll();
    }
}

