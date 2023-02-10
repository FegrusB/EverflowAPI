package com.example.everflowapi.repositories;


import com.example.everflowapi.models.Spid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface SpidRepository extends CrudRepository<Spid,Long> {
    void save(MultipartFile file);

    Spid findBySpid(String spId);


}
