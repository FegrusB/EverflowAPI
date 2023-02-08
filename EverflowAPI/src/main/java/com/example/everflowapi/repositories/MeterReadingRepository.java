package com.example.everflowapi.repositories;

import com.example.everflowapi.models.MeterReading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface MeterReadingRepository extends CrudRepository<MeterReading, Long> {
    void save(MultipartFile file);
}
