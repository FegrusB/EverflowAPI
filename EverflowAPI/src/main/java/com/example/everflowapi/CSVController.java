package com.example.everflowapi;

import com.example.everflowapi.helpers.CSVHelper;
import com.example.everflowapi.services.CSVFactory;
import com.example.everflowapi.services.CSVServiceable;
import com.example.everflowapi.services.MeterReadingCSVService;
import com.example.everflowapi.services.SpidCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/")
public class CSVController {

    CSVFactory factory;

    @Autowired
    public CSVController(CSVFactory factory){this.factory = factory;}

    @PostMapping("/spid/upload")
    public ResponseEntity<String> uploadSpids(@RequestParam("file")MultipartFile file){
        return factory.returnService(UploadType.SPID).uploadFile(file);
    }

    @PostMapping("/reading/upload")
    public ResponseEntity<String> uploadReading(@RequestParam("file")MultipartFile file){
        return factory.returnService(UploadType.METERREADING).uploadFile(file);
    }

}
