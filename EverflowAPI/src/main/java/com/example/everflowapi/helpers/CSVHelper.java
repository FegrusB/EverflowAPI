package com.example.everflowapi.helpers;

import com.example.everflowapi.models.MeterReading;
import com.example.everflowapi.models.Spid;
import com.example.everflowapi.repositories.SpidRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CSVHelper {


    public static final String TYPE = "text/csv";

    public static class CSVResult {

        private final ArrayList data;
        private int numMissed;
        private int numSuccess;
        public CSVResult(ArrayList data, int numMissed, int numSuccess){
            this.data = data;
            this.numMissed = numMissed;
            this.numSuccess = numSuccess;
        }
        public ArrayList getData() {return data;}
        public int getNumMissed() {return numMissed;}
        public int getNumSuccess() {return numSuccess;}
        public void addFailure(){numMissed++;numSuccess--;}
    }

    public static boolean hasCSVFormat(MultipartFile file){
        return TYPE.equals(file.getContentType());
    }

    public static CSVResult csvToSpid(InputStream inputStream){
        int numMissed = 0;
        int numSuccess = 0;
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            ArrayList<Spid> spids = new ArrayList<>();
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withAllowMissingColumnNames(true));
            Iterable<CSVRecord> csvRecords  = csvParser.getRecords();

            for(CSVRecord record: csvRecords){
                try {
                    Spid spid = new Spid(record.get("SPId"), record.get("MeterSerial"), record.get("MeterManufacturer"), Integer.parseInt(record.get("MeterSewerageSize")),
                            Integer.parseInt(record.get("MeterWaterSize")), Float.parseFloat(record.get("YearlyVolumeEstimate")), Integer.parseInt(record.get("MeterType")) != 0,
                            Integer.parseInt(record.get("ReturnToSewer")), record.get("GeneralSPId"), Integer.parseInt(record.get("NumberOfReadDigits")),
                            record.get("MeterLocationDescription"), Integer.parseInt(record.get("MeterReadFrequency")));
                    spids.add(spid);
                    numSuccess++;
                } catch (Exception e){
                    numMissed ++;
                }
            }
            return new CSVResult( spids,numMissed,numSuccess);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file + " + e.getMessage());
        }
    }
    public static CSVResult csvToMeterReading(InputStream inputStream, SpidRepository repository){
        int numMissed = 0;
        int numSuccess = 0;
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            ArrayList<MeterReading> meterReadings = new ArrayList<>();
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withAllowMissingColumnNames(true));
            Iterable<CSVRecord> csvRecords  = csvParser.getRecords();

            for(CSVRecord record: csvRecords){
                try {
                    MeterReading reading = new MeterReading(repository.findBySpid(record.get("SPId")) , record.get("MeterSerial"), Timestamp.valueOf(record.get("ReadingDate")), Integer.parseInt(record.get("Reading")), Integer.parseInt(record.get("UsedForEstimate")) != 0,
                            Integer.parseInt(record.get("ManualReading")) != 0, Integer.parseInt(record.get("Rollover")) != 0, record.get("ReadType"), record.get("GeneralSPId"));
                    meterReadings.add(reading);
                    numSuccess++;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    numMissed ++;
                }
            }
            return new CSVResult(meterReadings,numMissed,numSuccess);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file + " + e.getMessage());
        }
    }

}
