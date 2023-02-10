package com.example.everflowapi;

import com.example.everflowapi.helpers.CSVHelper;
import com.example.everflowapi.services.SpidCSVService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class SpidCSVServiceTests {

    @Autowired
    SpidCSVService service;

    @Test
    public void saveDuplicateSet() throws IOException {

        InputStream in = getClass().getResourceAsStream("/Data/spids - spids.csv");
        MockMultipartFile file = new MockMultipartFile("file",in);
        CSVHelper.CSVResult result = null;

        int[] ints = service.save(file,result);

        Assertions.assertEquals(0,ints[0]);
        Assertions.assertEquals(11,ints[1]);

    }
    @Test
    public void saveUniqueSet() throws IOException {

        InputStream in = getClass().getResourceAsStream("/Data/spidsUniqueSet.csv");
        MockMultipartFile file = new MockMultipartFile("file",in);
        CSVHelper.CSVResult result = null;

        int[] ints = service.save(file,result);

        Assertions.assertEquals(2,ints[0]);
        Assertions.assertEquals(0,ints[1]);

    }




}
