package com.example.everflowapi.Batch;

import com.example.everflowapi.models.Spid;
import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<Spid,Spid> {
    public Spid process(Spid spid){
        System.out.println(spid.toString());
        return spid;
    }
}
