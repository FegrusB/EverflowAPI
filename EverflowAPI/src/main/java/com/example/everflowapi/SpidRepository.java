package com.example.everflowapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

@Repository
public interface SpidRepository extends CrudRepository<Spid,Integer> {
    void save(MultipartFile file);
}
