package com.example.everflowapi;

import com.example.everflowapi.helpers.CSVHelper;
import com.example.everflowapi.services.CSVServiceable;
import com.example.everflowapi.services.MeterReadingCSVService;
import com.example.everflowapi.services.SpidCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/")
public class CSVController {

    public static int missedLines;
    @Autowired
    SpidCSVService spidCSVService;

    @Autowired
    MeterReadingCSVService meterReadingCSVService;

    @PostMapping("/spid/upload")
    public ResponseEntity<ResponseMessage> uploadSpids(@RequestParam("file")MultipartFile file){

        return uploadFile(file,spidCSVService);
    }

    @PostMapping("/reading/upload")
    public ResponseEntity<ResponseMessage> uploadReading(@RequestParam("file")MultipartFile file){
        return uploadFile(file,meterReadingCSVService);
    }

    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file, CSVServiceable CSVService){
        String message;
        missedLines  = 0;
        if(CSVHelper.hasCSVFormat(file)){
            try{
                CSVService.save(file);
                message = "Uploaded file successfully: " + file.getOriginalFilename() + " with " + missedLines + " missed line(s).";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e){
                message = "Could not upload file: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message + ": " + e.getMessage()));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

}
