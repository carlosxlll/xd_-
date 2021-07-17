-- 设置安全等级
SET SQL_SAFE_UPDATES = 0;

SET NAMES 'utf8';
USE bus_company;


-- 清除数据
TRUNCATE TABLE violation_record;
TRUNCATE TABLE line_leader;
DELETE FROM driver;
DELETE FROM car;
DELETE FROM line;
DELETE FROM fleet;
DELETE FROM leader;
DELETE FROM worker;


-- 插入数据
-- 插入worker表数据
INSERT INTO worker(worker_id, name, sex, profession) VALUES
(0, '陈宏朗', '男', '司机'),
(1, '杜花泽', '女', '司机'),
(2, '萧献仪', '女', '司机'),
(3, '潘文瑞', '男', '司机'),
(4, '谢伟志', '男', '司机'),
(5, '孔灵韵', '女', '司机'),
(6, '刘明俊', '男', '司机'),
(7, '蒋希月', '女', '队长'),
(8, '田彩静', '女', '司机'),
(9, '孔桂月', '女', '司机'),
(10, '吴浦泽', '男', '司机'),
(11, '邱问雁', '女', '司机'),
(12, '于阳羽', '男', '司机'),
(13, '钟安民', '男', '司机'),
(14, '苏泽琴', '女', '队长'),
(15, '夏鹏飞', '男', '司机'),
(16, '魏飞荷', '女', '司机'),
(17, '戴天翰', '男', '司机'),
(18, '唐春梅', '女', '司机'),
(19, '袁锐思', '男', '司机'),
(20, '汤妍凌', '女', '司机');


-- 插入leader表数据
INSERT INTO leader(worker_id) VALUES
(7),
(13);


-- 插入fleet表数据
INSERT INTO fleet(fleet_id, name, leader_id, remark) VALUES
(0, '西电一队', 7, NULL),
(1, '西电二队', 13, NULL);


-- 插入line表数据
INSERT INTO line(line_id, name, fleet_id, remark) VALUES
(0, '333路', 0, NULL),
(1, '734路', 0, NULL),
(2, '732路', 1, NULL);


-- 插入car表数据
INSERT INTO car(car_id, line_id, seat, remark) VALUES
('陕A00001', 0, 30, NULL),
('陕A00002', 0, 30, NULL),
('陕A00003', 0, 30, NULL),
('陕A00004', 1, 30, NULL),
('陕A00005', 1, 30, NULL),
('陕B00001', 1, 40, NULL),
('陕B00002', 1, 40, NULL),
('陕B00003', 2, 40, NULL),
('陕B00004', 2, 40, NULL),
('陕B00005', 2, 40, NULL);


-- 插入driver表数据
INSERT INTO driver(worker_id, line_id) VALUES
(0, 0),
(1, 0),
(2, 0),
(3, 0),
(4, 0),
(5, 0),
(6, 0),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 2),
(15, 2),
(16, 2),
(17, 2),
(18, 2),
(19, 2),
(20, 2);


-- 插入line_leader表数据
INSERT INTO line_leader(worker_id) VALUES
(0),
(7),
(14);


-- 插入violation_record表数据
INSERT INTO violation_record(violation_record_id, driver_id, car_id, type, time, port) VALUES
(1, 2, '陕A00003', '未礼让斑马线', '2020-11-25 08:00:00', '羊元村'),
(2, 5, '陕A00001', '压线', '2020-11-26 08:40:12', '康家湾'),
(3, 7, '陕B00001', '未礼让斑马线', '2020-11-27 19:20:00', '郭杜十字'),
(4, 3, '陕A00003', '压线', '2020-11-28 20:30:00', '木塔寨'),
(5, 20, '陕B00005', '违章停车', '2020-11-19 11:50:00', '北雷村');


