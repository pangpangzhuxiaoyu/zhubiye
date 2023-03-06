
use zhulun;
-- 创建用户表
CREATE TABLE tb_user(
	id int primary key auto_increment,
	username varchar(20) unique,
	password varchar(32)
);

-- 添加数据
INSERT INTO tb_user(username,password) values('延边','123'),('大学','234');

SELECT * FROM tb_user;