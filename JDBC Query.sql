CREATE DATABASE IF NOT EXISTS voipdata;
USE voipdata;



DROP TABLE IF EXISTS PROVIDERDATA;


CREATE TABLE PROVIDERDATA(
	NetworkId INTEGER AUTO_INCREMENT PRIMARY KEY,
    NetworkName VARCHAR(20),
	MarketShare float(4),
    PrepaidSub float(4),
    BillSub float(4)
    );

SELECT 'INSERTING DATA INTO DATABASE';

INSERT INTO PROVIDERDATA VALUES ( null, 'Eir', '18.8', '53.33', '46.7' );
INSERT INTO PROVIDERDATA VALUES ( null, 'Vodafone', '37.8', '42.5', '57.5' );
INSERT INTO PROVIDERDATA VALUES ( null, 'Three', '35.6', '43.2', '56.8' );
INSERT INTO PROVIDERDATA VALUES ( null, 'Tesco Mobile', '5.9', '85.1', '14.9' );


DROP TABLE IF EXISTS MARKETDATA;


CREATE TABLE MARKETDATA(
	YearlyInt integer(4),
    UsageRevenue float(16),
	SMSRevenue float(16),
    DataRevenue float(16),
    SMSTr float(16),
    DataTr float(16),
    DataId INTEGER AUTO_INCREMENT PRIMARY KEY,
    VoiceMinutes float(16),
    NetworkName varchar(20)
    );

SELECT 'INSERTING DATA INTO DATABASE';

INSERT INTO MARKETDATA VALUES ( 2010, '76927.15', '8230.07', '11781.58', '560204.46', '749635.9', null, '498228.576', 'Eir' );
INSERT INTO MARKETDATA VALUES ( 2010, '54672.68', '16547.70', '23688.50', '1126368.55', '1507246.65', null, '1001757.456', 'Vodafone' );
INSERT INTO MARKETDATA VALUES ( 2010, '145670.57', '15584.61', '22309.80', '1060812.71', '1419523.3', null, '943454.112', 'Three' );
INSERT INTO MARKETDATA VALUES ( 2010, '24142.03', '2582.84', '36970412', '175808.84', '235258.07', null, '156358.968', 'Tesco Mobile' );
INSERT INTO MARKETDATA VALUES ( 2011, '78853.40', '7618.13', '12064.33', '567447.35', '1046579.83', null, '520040.148', 'Eir' );
INSERT INTO MARKETDATA VALUES ( 2011, '158545.67', '15317.31', '24257.01', '1140931.38', '2104293.49', null, '1045612.638', 'Vodafone' );
INSERT INTO MARKETDATA VALUES ( 2011, '149318.14', '14425.83', '22845.23', '1074527.97', '1981821.38', null, '984756.876', 'Three' );
INSERT INTO MARKETDATA VALUES ( 2011, '24746.54', '2390.79', '3786.14', '178081.88', '328447.92', null, '163204.089', 'Tesco Mobile' );
INSERT INTO MARKETDATA VALUES ( 2012, '7477703.76', '7284.24', '13595.59', '591100.2', '1213036.34', null, '519632.752', 'Eir' );
INSERT INTO MARKETDATA VALUES ( 2012, '150350.25', '14645.98', '27335.82', '1188488.7', '2438977.33', null, '1044793.512', 'Vodafone' );
INSERT INTO MARKETDATA VALUES ( 2012, '141599.71', '13793.57', '25744.85', '1119317.4', '2297026.27', null, '983985.424', 'Three' );
INSERT INTO MARKETDATA VALUES ( 2012, '23467.36', '2286.01', '4266.70', '185504.85', '380686.93', null, '163076.236', 'Tesco Mobile' );
INSERT INTO MARKETDATA VALUES ( 2013, '72981.6', '7506.27', '16942.37', '459290.76', '1511921.38', null, '512370.124', 'Eir' );
INSERT INTO MARKETDATA VALUES ( 2013, '146739.6', '15092.40', '34064.98', '923467.60', '3039927.03', null, '1030190.99', 'Vodafone' );
INSERT INTO MARKETDATA VALUES ( 2013, '138199.2', '14214.01', '32082.36', '869720.81', '2863000.06', null, '970232.78', 'Three' );
INSERT INTO MARKETDATA VALUES ( 2013, '22903.8', '2355.69', '5317.02', '144139.12', '474485.96', null, '160797.00', 'Tesco Mobile' );
INSERT INTO MARKETDATA VALUES ( 2014, '71880.48', '5364.01', '20529.41', '352556.4', '2579261.86', null, '552338.54', 'Eir' );
INSERT INTO MARKETDATA VALUES ( 2014, '144525.65', '10785.09', '41277.22', '708863.4', '5185962.68', null, '1110553.03', 'Vodafone' );
INSERT INTO MARKETDATA VALUES ( 2014, '136114.10', '10157.39', '38874.84', '667606.8', '4884164.16', null, '1045917.67', 'Three' );
INSERT INTO MARKETDATA VALUES ( 2014, '22558.23', '1683.38', '6442.74', '110642.7', '809449.20', null, '173340.28', 'Tesco Mobile' );
INSERT INTO MARKETDATA VALUES ( 2015, '73103.04', '4972.78', '19178.44', '318422.18', '4581274.61', null, '577448.39', 'Eir' );
INSERT INTO MARKETDATA VALUES ( 2015, '146983.78', '9998.47', '38560.91', '640231.83', '9211286.19', null, '1161039.85', 'Vodafone' );
INSERT INTO MARKETDATA VALUES ( 2015, '138429.17', '9416.55', '36316.62', '602969.66', '8675179.59', null, '1093466.10', 'Three' );
INSERT INTO MARKETDATA VALUES ( 2015, '22941.91', '1560.60', '6018.76', '99930.36', '1437740.43', null, '181220.50', 'Tesco Mobile' );
INSERT INTO MARKETDATA VALUES ( 2016, '73007.73', '4117.76', '19150.43', '265929.38', '7282227.75', null, '589384.51', 'Eir' );
INSERT INTO MARKETDATA VALUES ( 2016, '146792.14', '8279.33', '38504.59', '534687.80', '14641926.01', null, '1185039.07', 'Vodafone' );
INSERT INTO MARKETDATA VALUES ( 2016, '138248.68', '7797.46', '36263.58', '503568.40', '13789750.42', null, '1116068.54', 'Three' );
INSERT INTO MARKETDATA VALUES ( 2016, '22912.00', '1292.27', '6009.97', '83456.56', '2285379.98', null, '184966.41', 'Tesco Mobile' );
INSERT INTO MARKETDATA VALUES ( 2017, '72509.53', '3760', '17355.40', '229132.89', '12295029.67', null, '581630.82', 'Eir' );
INSERT INTO MARKETDATA VALUES ( 2017, '145790.44', '7560', '34895.44', '460703.37', '24720857.53', null, '1169449.21', 'Vodafone' );
INSERT INTO MARKETDATA VALUES ( 2017, '137305.28', '7120', '32864.49', '433889.95', '23282077.46', null, '1101386.03', 'Three' );
INSERT INTO MARKETDATA VALUES ( 2017, '22755.65', '1180', '5446.64', '71908.72', '3858546.54', null, '182533.07', 'Tesco Mobile' );
INSERT INTO MARKETDATA VALUES ( 2018, '72809.20', '3513.53', '15634.08', '206805.26', '17947303.63', null, '590932.50', 'Eir' );
INSERT INTO MARKETDATA VALUES ( 2018, '146392.97', '7064.44', '31434.48', '415810.58', '36085536.02', null, '1188151.52', 'Vodafone' );
INSERT INTO MARKETDATA VALUES ( 2018, '137872.74', '6653.28', '29604.96', '391609.96', '33985319.64', null, '1118999.84', 'Three' );
INSERT INTO MARKETDATA VALUES ( 2018, '22849.69', '1102.65', '4906.44', '64901.65', '5632398.47', null, '185452.22', 'Tesco mobile' );


DROP TABLE IF EXISTS TOTALDATA;


CREATE TABLE TOTALDATA(
	Yearly integer(4),
    TotalUsageRevenue float(8),
	AvgSMSRevenue float(8),
    AvgDataRevenue float(8),
    SMSTraffic float(8),
    DataTraffic float(8),
    MobileVoiceMinutes float(8)
    );

INSERT INTO TOTALDATA VALUES ( 2010, '409187', '43777', '62668', '2979811', '3987425', '2650152' );
INSERT INTO TOTALDATA VALUES ( 2011, '419433', '40522', '64172', '3018337', '5566914', '2766171' );
INSERT INTO TOTALDATA VALUES ( 2012, '397752', '38746', '72317', '3144150', '6452321', '2764004' );
INSERT INTO TOTALDATA VALUES ( 2013, '388200', '39927', '90119', '2443036', '8042135', '2725373' );
INSERT INTO TOTALDATA VALUES ( 2014, '382343', '28532', '109199', '1875300', '13719478', '2937971' );
INSERT INTO TOTALDATA VALUES ( 2015, '388846', '26451', '102013', '1693735', '24368482', '3071534' );
INSERT INTO TOTALDATA VALUES ( 2016, '388339', '21903', '101864', '1414518', '38735254', '3135024' );
INSERT INTO TOTALDATA VALUES ( 2017, '385689', '20000', '92316', '1218792', '65399094', '3093781' );
INSERT INTO TOTALDATA VALUES ( 2018, '387283', '18689', '83160', '1100028', '95464381', '3143258' );


#DROP TABLE IF EXISTS marketdata_audit;

#CREATE TABLE marketdata_audit(
#	YearlyInt integer(4),
#    UsageRevenue float(16),
#	SMSRevenue float(16),
#    DataRevenue float(16),
#    SMSTr float(16),
#    DataTr float(16),
#   DataId INTEGER AUTO_INCREMENT PRIMARY KEY,
#    VoiceMinutes float(16),
#    NetworkName varchar(20)
#    );
    
#DELIMITER //
#DROP TRIGGER IF EXISTS marketdata_after_delete//
#CREATE TRIGGER marketdata_after_delete AFTER DELETE ON MARKETDATA
#    FOR EACH ROW
#        BEGIN
#            INSERT INTO marketdata_audit VALUES
#            (OLD.YearlyInt, OLD.UsageRevenue, OLD.SMSRevenue,
#				OLD.DataRevenue, OLD.SMSTr, OLD.DataTr, OLD.DataId,
#                OLD.VoiceMinutes, OLD.NetworkName, "DELETED", NOW());
#        END//
      
#DROP TRIGGER IF EXISTS marketdata_after_insert//
#CREATE TRIGGER marketdata_after_insert AFTER INSERT ON MARKETDATA
#    FOR EACH ROW
#        BEGIN
#            INSERT INTO marketdata_audit VALUES
#            (NEW.YearlyInt, NEW.UsageRevenue, NEW.SMSRevenue,
#				NEW.DataRevenue, NEW.SMSTr, NEW.DataTr, NEW.DataId,
#                NEW.VoiceMinutes, NEW.NetworkName, "INSERTED", NOW());            
#        END//
       
#DROP TRIGGER IF EXISTS marketdata_after_update//
#CREATE TRIGGER marketdata_after_update AFTER UPDATE ON MARKETDATA
#    FOR EACH ROW
#        BEGIN
#            INSERT INTO marketdata_audit VALUES
#            (OLD.YearlyInt, OLD.UsageRevenue, OLD.SMSRevenue,
#				OLD.DataRevenue, OLD.SMSTr, OLD.DataTr, OLD.DataId,
#                OLD.VoiceMinutes, OLD.NetworkName, "UPDATED", NOW());
#        END//

#DELIMITER ;




#select NetworkName, MarketShare from ProviderData group by Networkname;

DROP VIEW IF EXISTS SMSandDataStore;

CREATE VIEW SMSandDataStore AS
SELECT YearlyInt, SMSTr, DataTr
FROM MARKETDATA
INNER JOIN PROVIDERDATA ON MARKETDATA.NetworkName=PROVIDERDATA.NetworkName
ORDER BY MARKETDATA.YearlyInt;

#SELECT * FROM SMSandDataStore;



#DROP FUNCTION IF EXISTS calculate_revenue_by_provider;
#DELIMITER //

#CREATE Function calculate_revenue_by_provider
#(
#id_param INT
#)
#RETURNS DECIMAL(9,2)
#BEGIN
#	DECLARE revenue_var DECIMAL(9,2);
#		select sum((AvgSMSRevenue/100)*MarketShare)
#        into revenue_var
#        from RevenuesAndShare
#        where id = id_param;
#	RETURN revenue_var;
#END//

DROP PROCEDURE IF EXISTS update_market_data;

DELIMITER //
CREATE PROCEDURE update_market_data
(
	YearlyInt_param INTEGER(4),
    UsageRevenue_param float(8),
    SMSRevenue_param float(8),
    DataRevenue_param float(8),
    SMSTr_param float(8),
    DataTr_param float(8),
    DataId_param integer,
    VoiceMinutes_param float(8),
    NetworkName_param varchar(20)
)
BEGIN
	DECLARE sql_error TINYINT DEFAULT FALSE;
  
  Declare CONTINUE HANDLER FOR SQLEXCEPTION
    SET sql_error = TRUE;

	START TRANSACTION;  
	
	UPDATE voipdata.marketdata
	SET YearlyInt = YearlyInt_param,
    UsageRevenue = UsageRevenue_param,
	SMSRevenue = SMSRevenue_param,
	DataRevenue = DataRevenue_param,
	SMSTr = SMSTr_param,
	DataTr = DataTr_param,
    VoiceMinutes = VoiceMinutes_param,
    NetworkName = NetworkName_param
	WHERE DataId = DataId_param;
  
	IF sql_error = FALSE THEN
		COMMIT;
	ELSE
		ROLLBACK;
	END IF;
END//

DELIMITER ;

#Call update_market_data(2011, '654', 654, 654, 654, 654, 3, 654, 'Vodafone');

#Select * from marketdata;