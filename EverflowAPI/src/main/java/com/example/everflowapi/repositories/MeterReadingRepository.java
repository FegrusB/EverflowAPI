package com.example.everflowapi.repositories;

import com.example.everflowapi.models.MeterReading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterReadingRepository extends CrudRepository<MeterReading, Long> {

}
