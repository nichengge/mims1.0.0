use master
go
if exists(select * from sysdatabases where name='mims')drop database mims
go
create database mims
on(
name=mims_data,
filename='D:\database\mims.mdf',
size=10,
maxsize=50,
filegrowth=5)
log on(
name=mims_log,
filename='D:\database\mims.ldf',
size=5,
maxsize=25,
filegrowth=5
)
go
use mims
go
create table administrator(
aid varchar(20) primary key,
apwd varchar(15) not null
)	
create table staff(
	sidNo varchar(20) primary key,
	sname varchar(10) not null,
	ssex varchar(2) default '女' check (ssex in ('女','男')),
	spwd varchar(15) not null,
	tel varchar(15) not null,
	mobileNo varchar(15) not null,
	registerTime datetime not null
)
create table member(
	mname varchar(20) not null,
	msex varchar(20) default '女' check (msex in ('女','男')),
	birthday datetime,
	tel varchar(20),
	mobileNo varchar(20) not null,
	mailAddress varchar(20),
	mail varchar(20),
	mrank varchar(20) not null,
	mid varchar(20) not null primary key,
	examiner varchar(20) not null,
	registerTime datetime not null,
	credits float,
	consumeMoney float
)

create table credit(
	creditType varchar(10) not null,
	scale float
 )


insert into administrator(aid,apwd) values('admin','111111')

insert into staff values('s0001','刘杨杨','女','000000','321111121','132112311','2015-1-10')
insert into staff values('s0002','高柳','男','000000','321111122','132112312','2015-1-10')
insert into staff values('s0003','张军','女','000000','321111123','132112313','2015-1-10')
insert into staff values('s0004','李奥','男','000000','321111124','132112314','2015-1-10')
insert into staff values('s0005','刘欣','女','000000','321111125','132112315','2015-1-10')
insert into staff values('s0006','赵杨杨','男','000000','321111126','132112316','2015-1-10')

insert into member 
values('李杨','女','2015-01-10','112113223','1320200111','湖南长沙','322111@qq.com','普通卡','m00001','刘杨杨','2015-04-11',0,0)

insert into member 
values('刘柳','男','2015-01-10','112113223','1320200111','上海浦东','322112@qq.com','普通卡','m00002','刘欣','2015-04-11',0,0)

insert into member 
values('赵木棉','女','2015-01-10','112113223','1320200111','河南开封','322113@qq.com','普通卡','m00003','刘杨杨','2015-04-11',0,0)

insert into member 
values('范海','男','2015-01-10','112113223','1320200111','福建厦门','322114@qq.com','普通卡','m00004','赵杨杨','2015-04-11',0,0)

insert into member 
values('刘滔滔','女','2015-01-10','112113223','1320200111','北京','322115@qq.com','普通卡','m00005','刘杨杨','2015-04-11',0,0)

insert into member 
values('李逍遥','男','2015-01-10','112113223','1320200111','重庆','322116@qq.com','普通卡','m00006','李奥','2015-04-11',0,0)

insert into credit values('正价积分',1.0)
insert into credit values('推广积分',0.5)
insert into credit values('促销积分',0.25)
