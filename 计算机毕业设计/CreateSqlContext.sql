create database school_db;

use school_db;
create table Student(
student_id varchar(10) not null,
student_name varchar(10)  not null,
student_gender enum("男","女")  not null,
student_birth datetime  not null,
student_tel varchar(20)  not null,
student_adress varchar(50) not null,
primary key(student_id)
);

create table Course(
course_id varchar(20),
course_name varchar(20) NOT NULL,
primary key(course_id)
);

CREATE TABLE Score(
student_id VARCHAR(20),
course_id VARCHAR(20),
subject_score INT(3),
PRIMARY KEY(student_id,course_id)
);



insert into Student values('20230001' , '赵一' , '男', '1999-05-02' ,'13607871234','山东省济南市历下区文化东路1号');
insert into Student values('20230002' , '钱二' , '男', '2002-08-08' ,'13575598765','福建省厦门市思明区软件园二期观日路');
insert into Student values('20230003' , '孙三' , '女', '1999-09-22' ,'13756874123','广东省广州市天河区龙口东路7号');
insert into Student values('20230004' , '李四' , '女', '2000-06-11' ,'13685465219','四川省成都市武侯区天府大道南段999号');
insert into Student values('20230005' , '周五' , '男', '2003-02-17' ,'13865698741','山西省太原市小店区迎泽西大街37号');
insert into Student values('20230006' , '吴六' , '女', '1999-08-09' ,'13956874123','辽宁省沈阳市和平区中华路32号');
insert into Student values('20230007' , '郑七' , '女', '2004-04-28' ,'13756847983','浙江省杭州市西湖区紫荆花路128号');
insert into Student values('20230008' , '王哈' , '男', '2001-03-15' ,'13698745213','湖南省长沙市雨花区万家丽路289号');
insert into Student values('20230009' , '冯九' , '女', '2005-01-31' ,'13945872543','江西省南昌市青山湖区北京西路999号');
insert into Student values('20230010' , '陈十' , '男', '2003-09-11' ,'13652147892','福建省福州市闽侯县上街镇三江北路66号');
insert into Student values('20230011' , '褚十一' , '女', '2002-07-06' ,'13578456239','广西省南宁市青秀区朝阳路1号');
insert into Student values('20230012' , '卫十二' , '男', '2004-01-09' ,'13854219375','云南省昆明市西山区碧鸡路266号');
insert into Student values('20230013' , '蒋十三' , '女', '1999-11-21' ,'13659874523','河南省郑州市金水区经三路86号');
insert into Student values('20230014' , '沈十四' , '男', '2000-08-29' ,'13754216358','大连市星海广场');
insert into Student values('20230015' , '韩十五' , '女', '2002-05-05' ,'13952147892','江苏省南京市建邺区南湖路47号');
insert into Student values('20230016' , '杨十六' , '男', '2004-02-12' ,'13652147986','安徽省合肥市蜀山区望江西路69号');
insert into Student values('20230017' , '朱十七' , '男', '2003-06-18' ,'13758456987','黑龙江省哈尔滨市道外区民生路99号');
insert into Student values('20230018' , '秦十八' , '女', '2001-12-25' ,'13856987451','湖北省武汉市洪山区珞喻路1037号');
insert into Student values('20230019' , '尤十九' , '女', '2005-03-01' ,'13598745213','湖南省岳阳市岳阳楼区滨湖南路36号');
insert into Student values('20230020' , '许二十' , '男', '1999-10-08' ,'13698745264','四川省绵阳市涪城区涪城路29号');
insert into Student values('20230021' , '何二十一' , '男', '2004-07-29' ,'13798654721','山东省青岛市市南区香港中路17号');
insert into Student values('20230022' , '吕二十二' , '女', '2001-04-16' ,'13874562387','广东省深圳市福田区岗厦北路1023号');
insert into Student values('20230023' , '张二十三' , '男', '2003-11-23' ,'13948756213','浙江省宁波市江北区海曙路968号');
insert into Student values('20230024' , '谢二十四' , '男', '2000-09-20' ,'13587456392','辽宁省大连市西岗区黄河路36号');
insert into Student values('20230025' , '罗二十五' , '女', '1999-08-04' ,'13659874512','广西省桂林市象山区象山南路75号');
insert into Student values('20230026' , '汪二十六' , '女', '2001-01-06' ,'13787456219','福建省泉州市丰泽区新华南街30号');
insert into Student values('20230027' , '程二十七' , '男', '2002-05-14' ,'13856987451','湖北省黄石市西塞山区黄石大道89号');



use school_db;
insert into Course values('1001' , '语文' );
insert into Course values('1002' , '数学' );
insert into Course values('1003' , '英语' );
 

insert into Score values('20230001' , '1001' , 80);
insert into Score values('20230001' , '1002' , 90);
insert into Score values('20230001' , '1003' , 99);
insert into Score values('20230002' , '1001' , 70);
insert into Score values('20230002' , '1002' , 60);
insert into Score values('20230002' , '1003' , 80);
insert into Score values('20230003' , '1001' , 85);
insert into Score values('20230003' , '1002' , 92);
insert into Score values('20230003' , '1003' , 97);
insert into Score values('20230004' , '1001' , 76);
insert into Score values('20230004' , '1002' , 68);
insert into Score values('20230004' , '1003' , 82);
insert into Score values('20230005' , '1001' , 81);
insert into Score values('20230005' , '1002' , 87);
insert into Score values('20230005' , '1003' , 93);
insert into Score values('20230006' , '1001' , 69);
insert into Score values('20230006' , '1002' , 72);
insert into Score values('20230006' , '1003' , 75);
insert into Score values('20230007' , '1001' , 83);
insert into Score values('20230007' , '1002' , 89);
insert into Score values('20230007' , '1003' , 95);
insert into Score values('20230008' , '1001' , 73);
insert into Score values('20230008' , '1002' , 64);
insert into Score values('20230008' , '1003' , 79);
insert into Score values('20230009' , '1001' , 79);
insert into Score values('20230009' , '1002' , 84);
insert into Score values('20230009' , '1003' , 91);
insert into Score values('20230010' , '1001' , 67);
insert into Score values('20230010' , '1002' , 71);
insert into Score values('20230010' , '1003' , 74);
insert into Score values('20230011' , '1001' , 87);
insert into Score values('20230011' , '1002' , 91);
insert into Score values('20230011' , '1003' , 96);
insert into Score values('20230012' , '1001' , 78);
insert into Score values('20230012' , '1002' , 67);
insert into Score values('20230012' , '1003' , 81);
insert into Score values('20230013' , '1001' , 82);
insert into Score values('20230013' , '1002' , 88);
insert into Score values('20230013' , '1003' , 94);
insert into Score values('20230014' , '1001' , 70);
insert into Score values('20230014' , '1002' , 75);
insert into Score values('20230014' , '1003' , 78);
insert into Score values('20230015' , '1001' , 84);
insert into Score values('20230015' , '1002' , 90);
insert into Score values('20230015' , '1003' , 95);
insert into Score values('20230016' , '1001' , 75);
insert into Score values('20230016' , '1002' , 65);
insert into Score values('20230016' , '1003' , 80);
insert into Score values('20230017' , '1001' , 80);
insert into Score values('20230017' , '1002' , 86);
insert into Score values('20230017' , '1003' , 92);
insert into Score values('20230018' , '1001' , 68);
insert into Score values('20230018' , '1002' , 73);
insert into Score values('20230018' , '1003' , 76);
insert into Score values('20230019' , '1001' , 85);
insert into Score values('20230019' , '1002' , 91);
insert into Score values('20230019' , '1003' , 96);
insert into Score values('20230020' , '1001' , 76);
insert into Score values('20230020' , '1002' , 66);
insert into Score values('20230020' , '1003' , 81);
insert into Score values('20230021' , '1001' , 81);
insert into Score values('20230021' , '1002' , 87);
insert into Score values('20230021' , '1003' , 93);
insert into Score values('20230022' , '1001' , 69);
insert into Score values('20230022' , '1002' , 74);
insert into Score values('20230022' , '1003' , 77);
insert into Score values('20230023' , '1001' , 86);
insert into Score values('20230023' , '1002' , 92);
insert into Score values('20230023' , '1003' , 97);
insert into Score values('20230024' , '1001' , 77);
insert into Score values('20230024' , '1002' , 67);
insert into Score values('20230024' , '1003' , 82);
insert into Score values('20230025' , '1001' , 82);
insert into Score values('20230025' , '1002' , 88);
insert into Score values('20230025' , '1003' , 94);
insert into Score values('20230026' , '1001' , 70);
insert into Score values('20230026' , '1002' , 75);
insert into Score values('20230026' , '1003' , 78);
insert into Score values('20230027' , '1001' , 87);
insert into Score values('20230027' , '1002' , 93);
insert into Score values('20230027' , '1003' , 98);

select * from Student join Score on Score.student_id=Student.student_id where Score.course_id=1001;











