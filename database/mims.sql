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
	ssex varchar(2) default 'Ů' check (ssex in ('Ů','��')),
	spwd varchar(15) not null,
	tel varchar(15) not null,
	mobileNo varchar(15) not null,
	registerTime datetime not null
)
create table member(
	mname varchar(20) not null,
	msex varchar(20) default 'Ů' check (msex in ('Ů','��')),
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

insert into staff values('s0001','������','Ů','000000','321111121','132112311','2015-1-10')
insert into staff values('s0002','����','��','000000','321111122','132112312','2015-1-10')
insert into staff values('s0003','�ž�','Ů','000000','321111123','132112313','2015-1-10')
insert into staff values('s0004','���','��','000000','321111124','132112314','2015-1-10')
insert into staff values('s0005','����','Ů','000000','321111125','132112315','2015-1-10')
insert into staff values('s0006','������','��','000000','321111126','132112316','2015-1-10')

insert into member 
values('����','Ů','2015-01-10','112113223','1320200111','���ϳ�ɳ','322111@qq.com','��ͨ��','m00001','������','2015-04-11',0,0)

insert into member 
values('����','��','2015-01-10','112113223','1320200111','�Ϻ��ֶ�','322112@qq.com','��ͨ��','m00002','����','2015-04-11',0,0)

insert into member 
values('��ľ��','Ů','2015-01-10','112113223','1320200111','���Ͽ���','322113@qq.com','��ͨ��','m00003','������','2015-04-11',0,0)

insert into member 
values('����','��','2015-01-10','112113223','1320200111','��������','322114@qq.com','��ͨ��','m00004','������','2015-04-11',0,0)

insert into member 
values('������','Ů','2015-01-10','112113223','1320200111','����','322115@qq.com','��ͨ��','m00005','������','2015-04-11',0,0)

insert into member 
values('����ң','��','2015-01-10','112113223','1320200111','����','322116@qq.com','��ͨ��','m00006','���','2015-04-11',0,0)

insert into credit values('���ۻ���',1.0)
insert into credit values('�ƹ����',0.5)
insert into credit values('��������',0.25)
