create table if not exists user (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 name varchar(35),
 PHONE varchar(35)
);
create table if not exists `proxy` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 host varchar(35),
 port varchar(35),
 type varchar(35),
 source varchar(35),
 checkCount INT(10),
 failCount INT(10),
 successCount INT(10),
 createTime datetime default CURRENT_TIMESTAMP ,
 status INT(35)
);

ALTER TABLE `proxy` ADD unique(`host`);
-- insert into user(id,name,PHONE) values(784,'在写作协作','123456');
-- insert into user(id,name,phone) values(90,'是的','123456');
-- insert into user(id,name,phone) values(100,'zx','123456');