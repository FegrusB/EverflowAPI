SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `spids` (
    `_id` int NOT NULL AUTO_INCREMENT,
    `spid` varchar(20) NOT NULL,
    `MeterSerial` text,
    `MeterManufacturer` TEXT,
    `MeterSewerageSize` SMALLINT(255),
    `MeterWaterSize` SMALLINT(255),
    `YearlyVolumeEstimate` FLOAT(53),
    `MeterType` BOOlEAN,
    `ReturnToSewer` SMALLINT(255),
    `GeneralSPID` TEXT,
    `NumberOfReadDigits` INT,
    `MeterLocationDescription` TEXT,
    `MeterReadFrequency` INT,
    UNIQUE (`spid`),
    PRIMARY KEY (`_id`)
    ); 

CREATE TABLE `meterReadings` (
    `_id` int NOT NULL AUTO_INCREMENT,
    `spid` varchar(20) NOT NULL,
    `MeterSerial` TEXT,
    `ReadingDate` DATETIME(0),
    `Reading` INT,
    `UsedForEstimate` BIT(1),
    `ManualReading` Bit(1),
    `Rollover` BIT(1),
    `ReadType` CHAR,
    `GeneralSPID` TEXT,
    PRIMARY KEY (`_id`),
    FOREIGN KEY (`spid`) REFERENCES `spids`(`spid`)
);
 
INSERT INTO `spids` (`spid`,`MeterSerial`,`MeterManufacturer`,`MeterSewerageSize`,`MeterWaterSize`,`YearlyVolumeEstimate`,
                    `MeterType`,`ReturnToSewer`,`GeneralSPID`,`NumberOfReadDigits`,`MeterLocationDescription`,`MeterReadFrequency`) VALUES

                    ('3015084408W17','8382450','Invensys',15,15,89.127906,0,95,'3015084408',4,'RR BLDNG RR STORE BY SINK',3),
                    ('3009066592W19','14mu003815','SchlumbergerAquadInline(SAI)',15,15,2.016574,0,95,'3009066592',5,"F'court Opp Rh Gable End Abv   Chalenge Tyre Ctr Mobil Libary",3),
                    ('3022421605W19','06LU030158','ACTARI',15,15,142.578125,0,100,'3022421605',4,'**FP RH END OF GREEN FENCE',3),
                    ('3022557770W16','86115015','KENT',25,25,15,0,100,'3022557770',5,'*RIGHT OF ENTRY FRONT OF DOOR FISHING REPUBLIC.',3),
                    ('3022317867W11','94S000512','KENT',40,40,127.897574,0,100,'3022317867',5,'IN GDN 87 FELLSIDE ATGATE               OWNER HAS BUILT FENCE ON',3),
                    ('3028138517W10','98M311710','KENT-PSM',15,15,0,0,0,'3028138517',5,'IN RES COMPOUND TOP RH CNRNR. 1M LHS GREEN CABINET.',3),
                    ('3024422755W10','08M161403','ELSTER',15,15,314.121212,0,100,'3024422755',5,'LH MTR OF 2 LH CRNR OF PROP IN FP PROPNXT TO ROYAL SWAN PUB',3),
                    ('302649271XW1Y','3185011','KENT',15,15,258.297682,0,100,'302649271X',4,'ON RT IN BDY 20FT RT OF;RT WALL;',3),
                    ('3006438184W15','93030525','KENTMETERS',22,22,128.705234,0,100,'3006438184',4,'EXTERNAL CHAMBER GPS 20003 86898. FIRE HYDRANT  LHS OF DRIVE LEADING TO FARM- 20 YDS PAST BIG RD SIGN',3),
                    ('3021018035W15','13E640098R','Kent',20,20,2585,0,90,'3021018035',6,'in verge opp no. 35',3)