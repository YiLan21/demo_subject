CREATE TABLE IF NOT EXISTS`student1` (
  `stu_number` varchar(20) NOT NULL,
  `stu_name` varchar(20) DEFAULT NULL,
  `sub_number` int DEFAULT NULL,
  PRIMARY KEY (`stu_number`)
  );
  
  --
  CREATE TABLE IF NOT EXISTS`subject1` (
  `sub_number` varchar(20) NOT NULL,
  `sub_name` varchar(20) DEFAULT NULL,
  `sub_date` int DEFAULT NULL,
  `start_time` int DEFAULT NULL,
  `end_time` int DEFAULT NULL,
  `units` int DEFAULT NULL,
  PRIMARY KEY (`sub_number`)
) ;

-- 忘記怎麼連接SQL--