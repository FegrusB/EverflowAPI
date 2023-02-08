package com.example.everflowapi.services;

import org.springframework.web.multipart.MultipartFile;

public interface CSVServiceable {
    public void save(MultipartFile file);
}
