package com.example.everflowapi;


import com.example.everflowapi.helpers.CSVHelper;
import com.example.everflowapi.services.MeterReadingCSVService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class MeterReadingCSVServiceTests {

    @Autowired
    MeterReadingCSVService service;

    @Test
    public void savePerfectSet() throws IOException {

        InputStream in = getClass().getResourceAsStream("/Data/perfectReadings.csv");
        MockMultipartFile file = new MockMultipartFile("file",in);
        CSVHelper.CSVResult result = null;

        int[] ints = service.save(file,result);

        Assertions.assertEquals(9,ints[0]);
        Assertions.assertEquals(0,ints[1]);
    }

    @Test
    public void saveImperfectSet() throws IOException {

        InputStream in = getClass().getResourceAsStream("/Data/test1.csv");
        MockMultipartFile file = new MockMultipartFile("file",in);
        CSVHelper.CSVResult result = null;

        int[] ints = service.save(file,result);

        Assertions.assertEquals(9,ints[0]);
        Assertions.assertEquals(1,ints[1]);
    }

}
