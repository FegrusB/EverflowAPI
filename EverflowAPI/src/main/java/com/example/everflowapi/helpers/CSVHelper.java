package com.example.everflowapi.helpers;

import com.example.everflowapi.CSVController;
import com.example.everflowapi.models.MeterReading;
import com.example.everflowapi.models.Spid;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CSVHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){return false;}
        return true;
    }

    public static ArrayList<Spid> csvToSpid(InputStream inputStream){

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"))) {

            ArrayList<Spid> spids = new ArrayList<>();
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withAllowMissingColumnNames(true));
            Iterable<CSVRecord> csvRecords  = csvParser.getRecords();

            for(CSVRecord record: csvRecords){
                try {
                    Spid spid = new Spid(record.get("SPId"), record.get("MeterSerial"), record.get("MeterManufacturer"), Integer.parseInt(record.get("MeterSewerageSize")),
                            Integer.parseInt(record.get("MeterWaterSize")), Float.parseFloat(record.get("YearlyVolumeEstimate")), Boolean.parseBoolean(record.get("MeterType")),
                            Integer.parseInt(record.get("ReturnToSewer")), record.get("GeneralSPId"), Integer.parseInt(record.get("NumberOfReadDigits")),
                            record.get("MeterLocationDescription"), Integer.parseInt(record.get("MeterReadFrequency")));

                    spids.add(spid);
                } catch (Exception e){
                    CSVController.missedLines ++;
                }
            }
            return spids;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file + " + e.getMessage());
        }
    }
    public static ArrayList<MeterReading> csvToMeterReading(InputStream inputStream){

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"))) {

            ArrayList<MeterReading> meterReadings = new ArrayList<>();
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withAllowMissingColumnNames(true));
            Iterable<CSVRecord> csvRecords  = csvParser.getRecords();

            for(CSVRecord record: csvRecords){
                try {
                    MeterReading reading = new MeterReading(record.get("SPId"), record.get("MeterSerial"), Timestamp.valueOf(record.get("ReadingDate")), Integer.parseInt(record.get("Reading")),
                            Boolean.parseBoolean(record.get("UsedForEstimate")), Boolean.parseBoolean(record.get("ManualReading")), Boolean.parseBoolean(record.get("Rollover")),
                            record.get("ReadType"), record.get("GeneralSPId"));

                    meterReadings.add(reading);
                } catch (Exception e){
                    CSVController.missedLines ++;
                }
            }
            return meterReadings;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file + " + e.getMessage());
        }
    }

}
