package com.example.everflowapi.models;


import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.List;


@Entity
@Table(name = "spids")
public class Spid {

    private static int batchCounter = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id",columnDefinition = "int")
    private long id;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "spid")
    private List<MeterReading> spidList;

    @Column(unique = true)
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

    public Spid(String spid, String meterSerial, String meterManufacturer, int meterSewageSize, int meterWaterSize, float yearlyVolumeEstimate, boolean meterType,
                int returnToSewer, String generalSPID, int numberOfReadDigits, String meterLocationDescription, int meterReadFrequency) {
        this.spid = spid;
        this.meterSerial = meterSerial;
        this.meterManufacturer = meterManufacturer;
        this.meterSewageSize = meterSewageSize;
        this.meterWaterSize = meterWaterSize;
        this.yearlyVolumeEstimate = yearlyVolumeEstimate;
        this.meterType = meterType;
        this.returnToSewer = returnToSewer;
        this.generalSPID = generalSPID;
        this.numberOfReadDigits = numberOfReadDigits;
        this.meterLocationDescription = meterLocationDescription;
        this.meterReadFrequency = meterReadFrequency;
    }

    public Spid() {

    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Spid rhs = (Spid) obj;
        return new EqualsBuilder()
                .append(spid, rhs.spid)
                .append(meterSerial, rhs.meterSerial)
                .append(meterManufacturer, rhs.meterManufacturer)
                .append(meterSewageSize,rhs.meterSewageSize)
                .append(meterWaterSize,rhs.meterWaterSize)
                .append(yearlyVolumeEstimate,rhs.yearlyVolumeEstimate)
                .append(meterType,rhs.meterType)
                .append(returnToSewer,rhs.returnToSewer)
                .append(generalSPID,rhs.generalSPID)
                .append(numberOfReadDigits,rhs.numberOfReadDigits)
                .append(meterLocationDescription,rhs.meterLocationDescription)
                .append(meterReadFrequency,rhs.meterReadFrequency)
                .isEquals();
    }

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

    @Override
    public String toString() {
        return "Spid{" +
                "id=" + id +
                ", spidList=" + spidList +
                ", spid='" + spid + '\'' +
                ", meterSerial='" + meterSerial + '\'' +
                ", meterManufacturer='" + meterManufacturer + '\'' +
                ", meterSewageSize=" + meterSewageSize +
                ", meterWaterSize=" + meterWaterSize +
                ", yearlyVolumeEstimate=" + yearlyVolumeEstimate +
                ", meterType=" + meterType +
                ", returnToSewer=" + returnToSewer +
                ", generalSPID='" + generalSPID + '\'' +
                ", numberOfReadDigits=" + numberOfReadDigits +
                ", meterLocationDescription='" + meterLocationDescription + '\'' +
                ", meterReadFrequency=" + meterReadFrequency +
                '}';
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
