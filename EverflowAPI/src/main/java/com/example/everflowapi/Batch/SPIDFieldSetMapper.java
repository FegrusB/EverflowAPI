package com.example.everflowapi.Batch;

import com.example.everflowapi.models.Spid;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class SPIDFieldSetMapper implements FieldSetMapper<Spid> {
    @Override
    public Spid mapFieldSet(FieldSet fieldSet) throws BindException {
        Spid spid = new Spid(
                fieldSet.readString(0),
                fieldSet.readString(1),
                fieldSet.readString(2),
                fieldSet.readInt(3),
                fieldSet.readInt(4),
                fieldSet.readFloat(5),
                fieldSet.readBoolean(6),
                fieldSet.readInt(7),
                fieldSet.readString(8),
                fieldSet.readInt(9),
                fieldSet.readString(10),
                fieldSet.readInt(11)
        );

        return spid;
    }
}
