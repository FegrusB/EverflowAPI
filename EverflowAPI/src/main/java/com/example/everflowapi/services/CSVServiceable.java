package com.example.everflowapi.services;

import org.springframework.web.multipart.MultipartFile;

public interface CSVServiceable {
    int[] save(MultipartFile file);
}
