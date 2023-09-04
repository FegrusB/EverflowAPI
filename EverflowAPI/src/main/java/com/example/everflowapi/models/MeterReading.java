package com.example.everflowapi.models;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.sql.Timestamp;

@Entity
@Table(name = "meterReadings")
public class MeterReading {

   private static int batchCounter;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "_id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "spid" ,referencedColumnName = "spid")
    private Spid spid;

    @Column(name = "MeterSerial")
    private String meterSerial;

    @Column(name = "ReadingDate")
    private Timestamp readingDate;

    @Column(name = "Reading")
    private Integer reading;

    @Column(name = "UsedForEstimate")
    private Boolean usedForEstimate;

    @Column(name = "ManualReading")
    private Boolean manualReading;

    @Column(name = "Rollover")
    private Boolean rollover;

    @Column(name = "ReadType",columnDefinition = "char")
    private String readType;

    @Column(name = "GeneralSPID")
    private String generalSpid;

    public MeterReading(Spid spid,String meterSerial, Timestamp readingDate, Integer reading, Boolean usedForEstimate,
                        Boolean manualReading, Boolean rollover, String readType, String generalSpid) {

        this.spid = spid;
        this.meterSerial = meterSerial;
        this.readingDate = readingDate;
        this.reading = reading;
        this.usedForEstimate = usedForEstimate;
        this.manualReading = manualReading;
        this.rollover = rollover;
        this.readType = readType;
        this.generalSpid = generalSpid;
    }

    public MeterReading() {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        MeterReading rhs = (MeterReading) obj;
        return new EqualsBuilder()
                .append(spid.getSpid(), rhs.spid.getSpid())
                .append(meterSerial, rhs.meterSerial)
                .append(readingDate, rhs.readingDate)
                .append(reading,rhs.reading)
                .append(usedForEstimate,rhs.usedForEstimate)
                .append(manualReading,rhs.manualReading)
                .append(rollover,rhs.rollover)
                .append(readType,rhs.readType)
                .append(generalSpid,rhs.generalSpid)
                .isEquals();
    }

    public String getSpid() {
        return spid.getSpid();
    }

    public int getId() {return id;}

    public String getMeterSerial() {return meterSerial;}

    public void setMeterSerial(String meterSerial) {
        this.meterSerial = meterSerial;
    }

    public Timestamp getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Timestamp readingDate) {
        this.readingDate = readingDate;
    }

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }

    public Boolean getUsedForEstimate() {
        return usedForEstimate;
    }

    public void setUsedForEstimate(Boolean usedForEstimate) {
        this.usedForEstimate = usedForEstimate;
    }

    public Boolean getManualReading() {
        return manualReading;
    }

    public void setManualReading(Boolean manualReading) {
        this.manualReading = manualReading;
    }

    public Boolean getRollover() {
        return rollover;
    }

    public void setRollover(Boolean rollover) {
        this.rollover = rollover;
    }

    public String getReadType() {
        return readType;
    }

    public void setReadType(String readType) {
        this.readType = readType;
    }

    public String getGeneralSpid() {
        return generalSpid;
    }

    public void setGeneralSpid(String generalSpid) {
        this.generalSpid = generalSpid;
    }
    public static int getBatchCounter() {
        return batchCounter;
    }
    public static int getBatchCounterInc() {
        int old = batchCounter;
        batchCounter++;
        return old;
    }
}
