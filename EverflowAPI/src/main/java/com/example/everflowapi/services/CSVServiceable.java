package com.example.everflowapi.services;

import com.example.everflowapi.helpers.CSVHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface CSVServiceable {
    int[] save(MultipartFile file);

    default ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        String message;
        if(CSVHelper.hasCSVFormat(file)){
            try{
                int[] hitMiss = this.save(file);
                message = "Uploaded file successfully: " + file.getOriginalFilename() + " with " + hitMiss[0] + " Successful line(s) and " + hitMiss[1] + " missed line(s)";
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(message);
            } catch (Exception e){
                message = "Could not upload file: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).contentType(MediaType.APPLICATION_JSON).body(message + ": " + e.getMessage());
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(message);
    }

}
