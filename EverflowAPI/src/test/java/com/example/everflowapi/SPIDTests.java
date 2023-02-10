package com.example.everflowapi;

import com.example.everflowapi.helpers.CSVHelper;
import com.example.everflowapi.models.Spid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootTest
public class SPIDTests {


    @Test
    void csvToSpidTestPerfectSet() throws IOException {

        Spid sampleSPID = new Spid("3015084408W17","8382450","Invensys",15,15, 89.127906f,false,95,"3015084408",4,"RR BLDNG RR STORE BY SINK",3);

        InputStream in = getClass().getResourceAsStream("/Data/spids - spids.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        CSVHelper.CSVResult<Spid> result = CSVHelper.csvToSpid(in);

        Assertions.assertTrue(sampleSPID.equals(result.getData().get(0)));
        Assertions.assertEquals(11,result.getNumSuccess());
        Assertions.assertEquals(0,result.getNumMissed());

    }

    @Test
    void csvToSpidTestImperfectSet() throws IOException {

        Spid sampleSPID = new Spid("3021018035W15","13E640098R","Kent",20,20, 2585f,false,90,"3021018035",6,"in verge opp no. 35",3);

        InputStream in = getClass().getResourceAsStream("/Data/spidsImperfectSet.CSV");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        CSVHelper.CSVResult<Spid> result = CSVHelper.csvToSpid(in);

        Assertions.assertTrue(sampleSPID.equals(result.getData().get(10)));
        Assertions.assertEquals(11,result.getNumSuccess());
        Assertions.assertEquals(1,result.getNumMissed());

    }

}
