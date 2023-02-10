package com.example.everflowapi;

import com.example.everflowapi.helpers.CSVHelper;
import com.example.everflowapi.models.MeterReading;
import com.example.everflowapi.repositories.SpidRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;

@SpringBootTest
public class MeterReadingsTests {

    @Autowired
    SpidRepository repository;
    @Test
    void csvToMeterReadingTestPerfectSet() throws IOException {

        MeterReading sampleReading = new MeterReading(repository.findBySpid("3024422755W10"),"08M161403", Timestamp.valueOf("2017-12-17 00:00:00"),3532,false,false,false,"C","3024422755");

        InputStream in = getClass().getResourceAsStream("/Data/test1.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        CSVHelper.CSVResult result = CSVHelper.csvToMeterReading(in,repository);

        Assertions.assertTrue(sampleReading.equals(result.getData().get(0)));
        Assertions.assertEquals(10,result.getNumSuccess());
        Assertions.assertEquals(0,result.getNumMissed());

    }

    @Test
    void csvToMeterReadingTestImperfectSet() throws IOException {

        MeterReading sampleReading = new MeterReading(repository.findBySpid("3022317867W11"),"88129662",Timestamp.valueOf("2022-08-15 00:00:00"),22000,false,true,false,"0","3022317867");

        InputStream in = getClass().getResourceAsStream("/Data/meter_readings - meter_readings.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        CSVHelper.CSVResult result = CSVHelper.csvToMeterReading(in,repository);

        Assertions.assertTrue(sampleReading.equals(result.getData().get(157)));
        Assertions.assertEquals(158,result.getNumSuccess());
        Assertions.assertEquals(1,result.getNumMissed());

    }
}
