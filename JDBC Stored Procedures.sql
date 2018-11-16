DROP PROCEDURE IF EXISTS insert_market_data;

DELIMITER //
CREATE PROCEDURE insert_market_data
(
	Yearly_param INTEGER(4),
    TotalUsageRevenue_param float(8),
    AvgSMSRevenue_param float(8),
    AvgDataRevenue_param float(8),
    SMSTraffic_param float(8),
    DataTraffic_param float(8),
    DataId_param integer,
    VoiceCallMinutes_param float(8),
    NetworkName_param varchar(20)
)
BEGIN
  DECLARE sql_error TINYINT DEFAULT FALSE;
 
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    SET sql_error = TRUE;

    START TRANSACTION; 
   
    INSERT INTO voipdata.marketdata VALUES
    (
    Yearly_param,
    TotalUsageRevenue_param,
    AvgSMSRevenue_param,
    AvgDataRevenue_param,
    SMSTraffic_param,
    DataTraffic_param,
    DataId_param,
    VoiceCallMinutes_param,
    NetworkName_param
    );
 
    IF sql_error = FALSE THEN
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
END//

DELIMITER ;
