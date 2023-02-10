package com.example.everflowapi.services;

import com.example.everflowapi.helpers.CSVHelper;
import com.example.everflowapi.models.MeterReading;
import com.example.everflowapi.repositories.MeterReadingRepository;
import com.example.everflowapi.repositories.SpidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class MeterReadingCSVService implements CSVServiceable {

    @Autowired
    MeterReadingRepository repository;

    @Autowired
    SpidRepository spidRepository;

    public int[] save(MultipartFile file){

        CSVHelper.CSVResult<MeterReading> result;

        try{

            result = CSVHelper.csvToMeterReading(file.getInputStream(), spidRepository);

            for (MeterReading reading: result.getData()) {
                try {repository.save(reading);
                } catch (Exception e){result.addFailure();}
            }

        } catch (IOException e){
            throw new RuntimeException("Failed to store data" + e.getMessage());
        }
        return new int[] {result.getNumSuccess(),result.getNumMissed()};
    }
}

