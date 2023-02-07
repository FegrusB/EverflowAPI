SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE 'spids' (
    '_id' int(11) NOT NULL AUTO_INCREMENT,
    'spid' TINYTEXT,
    'MeterSerial' TINYTEXT,
    'MeterManufacturer' TINYTEXT,
    'MeterSewerageSize' SMALLINT(),
    'MeterWaterSize' SMALLINT(),
    'YearlyVolumeEstimate' FLOAT(),
    'MeterType' BOOlEAN,
    'ReturnToSewer' SMALLINT(),
    'GeneralSPID' TINYTEXT,
    'NumberOfReadDigits' TINYINT,
    'MeterLocationDescription' TEXT,
    'MeterReadFrequency' TINYINT,
    PRIMARY KEY ('_id')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE 'meterReadings' (
    '_id' int() NOT NULL AUTO_INCREMENT,
    'MeterSerial' TINYTEXT,
    'ReadingDate' SMALLDATETIME,
    'Reading' int,
    'UsedForEstimate'BOOlEAN,
    'ManualReading' BOOlEAN,
    'Rollover' BOOlEAN,
    'ReadType' CHAR,
    'GeneralSPID' TINYTEXT,
    PRIMARY KEY ('_id')
)