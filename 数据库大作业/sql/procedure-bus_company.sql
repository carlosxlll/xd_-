-- 需求函数

DELIMITER //


-- 1.录入司机基本信息，如工号、姓名、性别等；
DROP PROCEDURE IF EXISTS insert_driver_information //
CREATE PROCEDURE
insert_driver_information( driver_id1 INT, name1 TEXT, sex1 TEXT, profession1 TEXT ,line_id1 INT)
BEGIN
	INSERT INTO worker(worker_id, name, sex, profession) VALUES
    (driver_id1,name1, sex1, profession1);
    INSERT INTO driver(worker_id, line_id) VALUES
    (driver_id1, line_id1);
END//


-- 2.录入汽车基本信息，如车牌号、座数等；
DROP PROCEDURE IF EXISTS insert_car_information //
CREATE PROCEDURE
insert_car_information( car_id1 TEXT, line_id1 INT, seat1 INT, remark1 TEXT )
BEGIN
	INSERT INTO car(car_id, line_id, seat, remark) VALUES
    (car_id1,line_id1, seat1, remark1);
END//


-- 3.录入司机的违章信息；
DROP PROCEDURE IF EXISTS insert_driver_violation_information //
CREATE PROCEDURE
insert_driver_violation_information(driver_id1 INT, car_id1 TEXT, type1 TEXT, time1 DATETIME ,port1 TEXT)
BEGIN
	INSERT INTO violation_record(driver_id, car_id, type, time ,port) VALUES
    (driver_id1 , car_id1 , type1 , time1 ,port1 );
END//


-- 4.查询某个车队下的司机基本信息；
DROP PROCEDURE IF EXISTS get_driver_information_by_fleet //
CREATE PROCEDURE
get_driver_information_by_fleet( cd INT)
BEGIN
	SELECT name,sex,profession FROM worker WHERE worker_id IN (SELECT worker_id FROM driver WHERE line_id IN (SELECT line_id FROM line WHERE fleet_id=cd));
END
//


-- 5.查询某名司机在某个时间段的违章详细信息；
 DROP PROCEDURE IF EXISTS get_violation_record_by_driver_and_datetime //
CREATE PROCEDURE
get_violation_record_by_driver_and_datetime( sj INT, dt1 DATETIME, dt2 DATETIME)
BEGIN
	SELECT name,car_id,type,time,port FROM violation_record,worker WHERE time BETWEEN dt1 AND dt2 AND violation_record.driver_id=worker.worker_id AND violation_record.driver_id=sj;
END
//


-- 6.查询某个车队在某个时间段的违章统计信息，如：2次闯红灯、4次未礼让斑马线等。
 DROP PROCEDURE IF EXISTS get_violation_record_by_fleet_and_datetime2 //
CREATE PROCEDURE
get_violation_record_by_fleet_and_datetime2( cd INT, dt1 DATETIME, dt2 DATETIME)
BEGIN
	SELECT type, COUNT(violation_record_id) FROM violation_record WHERE time BETWEEN dt1 AND dt2 AND driver_id IN (SELECT driver_id FROM driver WHERE line_id IN (SELECT line_id FROM line WHERE fleet_id=cd)) GROUP BY type;
END
//


-- 7.查询人员信息。
 DROP PROCEDURE IF EXISTS get_driver_information //
CREATE PROCEDURE
get_driver_information( cd INT)
BEGIN
    SELECT name,sex,profession FROM worker WHERE worker_id=cd ;
END
//

-- 7.查询某条路上人员信息。
 DROP PROCEDURE IF EXISTS get_driver_information_by_line //
CREATE PROCEDURE
get_driver_information_by_line( cd INT)
BEGIN
    SELECT name,sex,profession FROM worker,driver WHERE profession='司机' AND worker.worker_id IN (SELECT driver.worker_id WHERE line_id=cd) ;
END
//

-- 8.查询某条路上车辆信息。
 DROP PROCEDURE IF EXISTS get_car_information_by_line //
CREATE PROCEDURE
get_car_information_by_line( cd INT)
BEGIN
    SELECT car_id,seat FROM car WHERE line_id=cd;
END
//

-- 9.查询某条路上违章信息。
 DROP PROCEDURE IF EXISTS get_record_information_by_line //
CREATE PROCEDURE
get_record_information_by_line( cd INT, dt1 DATETIME, dt2 DATETIME)
BEGIN
    SELECT  driver_id, car_id, type, time, port FROM violation_record,driver WHERE violation_record.time BETWEEN dt1 AND dt2 AND violation_record.driver_id IN (SELECT driver.worker_id WHERE line_id=cd) ;
END
//

-- 10.查询某车队上车辆信息。
 DROP PROCEDURE IF EXISTS get_car_information_by_fleet //
CREATE PROCEDURE
get_car_information_by_fleet( cd INT)
BEGIN
    SELECT car_id,seat FROM car WHERE line_id IN(SELECT line_id FROM line WHERE fleet_id=cd);
END
//
DELIMITER ;


-- --  CALL insert_driver_information(50,'张帅豪' ,'男','司机',1);
-- -- CALL insert_car_information( '陕B0006', 2, 40, NULL );
-- -- CALL insert_driver_violation_information(4, '陕A00003', '压线', '2020-12-02 00:00:00' ,'太白路口');
-- -- CALL get_driver_information_by_fleet(1);
-- -- CALL get_violation_record_by_driver_and_datetime('萧献仪', '2020-01-01 00:00:00', '2020-12-30 00:00:00');
-- -- CALL get_violation_record_by_fleet_and_datetime2(0, '2020-01-01 00:00:00', '2020-12-30 00:00:00');
