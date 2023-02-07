package com.example.everflowapi;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;



@Entity
@Table(name = "spids")
public class Spid {

    @Id
    @Column(name = "_id")
    private long id;

    @Column(name = "spid")
    private String spid;

    @Column(name = "MeterSerial")
    private String meterSerial;

    @Column(name = "MeterManufacturer")
    private String meterManufacturer;

    @Column(name = "MeterSewerageSize")
    private int meterSewageSize;

    @Column(name = "MeterWaterSize")
    private int meterWaterSize;

    @Column(name = "YearlyVolumeEstimate")
    private float yearlyVolumeEstimate;

    @Column(name = "MeterType")
    private boolean meterType;

    @Column(name = "ReturnToSewer")
    private int returnToSewer;

    @Column(name = "GeneralSPID")
    private String generalSPID;

    @Column(name = "NumberOfReadDigits")
    private int numberOfReadDigits;

    @Column(name = "MeterLocationDescription")
    private String meterLocationDescription;

    @Column(name = "MeterReadFrequency")
    private int meterReadFrequency;


    public long getId() {
        return id;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getMeterSerial() {
        return meterSerial;
    }

    public void setMeterSerial(String meterSerial) {
        this.meterSerial = meterSerial;
    }

    public String getMeterManufacturer() {
        return meterManufacturer;
    }

    public void setMeterManufacturer(String meterManufacturer) {
        this.meterManufacturer = meterManufacturer;
    }

    public int getMeterSewageSize() {
        return meterSewageSize;
    }

    public void setMeterSewageSize(int meterSewageSize) {
        this.meterSewageSize = meterSewageSize;
    }

    public int getMeterWaterSize() {
        return meterWaterSize;
    }

    public void setMeterWaterSize(int meterWaterSize) {
        this.meterWaterSize = meterWaterSize;
    }

    public float getYearlyVolumeEstimate() {
        return yearlyVolumeEstimate;
    }

    public void setYearlyVolumeEstimate(float yearlyVolumeEstimate) {
        this.yearlyVolumeEstimate = yearlyVolumeEstimate;
    }

    public boolean isMeterType() {
        return meterType;
    }

    public void setMeterType(boolean meterType) {
        this.meterType = meterType;
    }

    public int getReturnToSewer() {
        return returnToSewer;
    }

    public void setReturnToSewer(int returnToSewer) {
        this.returnToSewer = returnToSewer;
    }

    public String getGeneralSPID() {
        return generalSPID;
    }

    public void setGeneralSPID(String generalSPID) {
        this.generalSPID = generalSPID;
    }

    public int getNumberOfReadDigits() {
        return numberOfReadDigits;
    }

    public void setNumberOfReadDigits(int numberOfReadDigits) {
        this.numberOfReadDigits = numberOfReadDigits;
    }

    public String getMeterLocationDescription() {
        return meterLocationDescription;
    }

    public void setMeterLocationDescription(String meterLocationDescription) {
        this.meterLocationDescription = meterLocationDescription;
    }

    public int getMeterReadFrequency() {
        return meterReadFrequency;
    }

    public void setMeterReadFrequency(int meterReadFrequency) {
        this.meterReadFrequency = meterReadFrequency;
    }
}
