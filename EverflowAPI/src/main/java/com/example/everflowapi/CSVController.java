package com.example.everflowapi;

import com.example.everflowapi.Batch.JobFactory;
import com.example.everflowapi.services.CSVFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/api/")
public class CSVController {

    CSVFactory csvFactory;
    JobFactory jobFactory;

    @Autowired
    public CSVController(CSVFactory csvFactory,JobFactory jobFactory){
        this.csvFactory = csvFactory;
        this.jobFactory = jobFactory;
    }

    @PostMapping("/spid/upload")
    public void uploadSpids(@RequestParam("file")MultipartFile file) throws Exception{

        File diskFile = new File(file.getName()+ Math.floor( Math.random() * 10) + ".csv");
        diskFile.createNewFile();
        file.transferTo(diskFile.getAbsoluteFile());
        jobFactory.runJob(diskFile,UploadType.SPID);
    }

    @PostMapping("/reading/upload")
    public ResponseEntity<String> uploadReading(@RequestParam("file")MultipartFile file){
        return csvFactory.returnService(UploadType.METERREADING).uploadFile(file);
    }

}
