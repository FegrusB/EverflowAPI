package com.example.everflowapi;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpidCSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERS = {"SPId","MeterSerial","MeterManufacturer","MeterSewerageSize","MeterWaterSize",
            "YearlyVolumeEstimate","MeterType","ReturnToSewer","GeneralSPId","NumberOfReadDigits","MeterLocationDescription","MeterReadFrequency"};

    public static boolean hasCSVFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){return false;}
        return true;
    }

    public static ArrayList<Spid> csvToSpid(InputStream inputStream){

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"))) {

            ArrayList<Spid> spids = new ArrayList<>();
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            Iterable<CSVRecord> csvRecords  = csvParser.getRecords();

            for(CSVRecord record: csvRecords){
                Spid spid = new Spid(record.get("SPId"),record.get("MeterSerial"),record.get("MeterManufacturer"),Integer.parseInt(record.get("MeterSewerageSize")),
                        Integer.parseInt(record.get("MeterWaterSize")),Float.parseFloat(record.get("YearlyVolumeEstimate")),Boolean.parseBoolean(record.get("MeterType")),
                        Integer.parseInt(record.get("ReturnToSewer")),record.get("GeneralSPId"), Integer.parseInt(record.get("NumberOfReadDigits")),
                        record.get("MeterLocationDescription"), Integer.parseInt(record.get("MeterReadFrequency")));

                spids.add(spid);
            }
            return spids;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file + " + e.getMessage());
        }
    }

}
