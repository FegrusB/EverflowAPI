SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE spids (
    _id int NOT NULL AUTO_INCREMENT,
    spid TEXT,
    MeterSerial text,
    MeterManufacturer TEXT,
    MeterSewerageSize SMALLINT(255),
    MeterWaterSize SMALLINT(255),
    YearlyVolumeEstimate FLOAT(53),
    MeterType BOOlEAN,
    ReturnToSewer SMALLINT(255),
    GeneralSPID TEXT,
    NumberOfReadDigits INT,
    MeterLocationDescription TEXT,
    MeterReadFrequency INT,
    PRIMARY KEY (_id)
); 

CREATE TABLE meterReadings (
    _id int NOT NULL AUTO_INCREMENT,
    MeterSerial TEXT,
    ReadingDate DATETIME(0),
    Reading INT,
    UsedForEstimate BIT(1),
    ManualReading Bit(1),
    Rollover BIT(1),
    ReadType CHAR,
    GeneralSPID TEXT,
    PRIMARY KEY (_id)
);