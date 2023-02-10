package com.example.everflowapi.services;

import com.example.everflowapi.helpers.CSVHelper;
import org.springframework.web.multipart.MultipartFile;

public interface CSVServiceable {
    int[] save(MultipartFile file);
}
