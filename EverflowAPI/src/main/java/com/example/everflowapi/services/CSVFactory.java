package com.example.everflowapi.services;

import com.example.everflowapi.UploadType;
import org.springframework.stereotype.Service;

@Service
public class CSVFactory {
    private final MeterReadingCSVService meterReadingCSVService;
    private final SpidCSVService spidCSVService;


    public CSVFactory(MeterReadingCSVService meterReadingCSVService, SpidCSVService spidCSVService) {
        this.meterReadingCSVService = meterReadingCSVService;
        this.spidCSVService = spidCSVService;
    }

    public CSVServiceable returnService(UploadType type){
        switch (type) {
            case SPID -> {
                return spidCSVService;
            }
            case METERREADING -> {
                return meterReadingCSVService;
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
