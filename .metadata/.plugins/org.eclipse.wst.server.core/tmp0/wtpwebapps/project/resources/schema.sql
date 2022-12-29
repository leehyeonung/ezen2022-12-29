--2022-11-30

use javadb;

create table member2(
email varchar(100) not null,
pwd varchar(100) not null,
nick_name varchar(100) not null,
reg_at datetime default current_timestamp,
last_login datetime
);

create table board(
	bno int not null auto_increment,
	title varchar(100) not null,
	writer varchar(100) not null,
	content text?,
	reg_Date timestamp default current_timestamp,
	read_count int default 0);
	
create table comment(
cno int not null auto_increment,
bno int default -1,
writer varchar(100) default "unknown",
content varchar(1000) not null,
reg_at datetime default current_timestamp,
primary key(cno));