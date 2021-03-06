--连接老师服务器
--conn ysjk2/123456@211.82.169.249/orcl

--创建序列
CREATE   SEQUENCE example_sequence
INCREMENT BY 1
START WITH 1 
NOMAXVALUE 
NOCYCLE 
NOCACHE

--创建触发器
 CREATE  TRIGGER example_triger
 BEFORE INSERT ON users FOR EACH ROW  WHEN (new.id is null)
 begin
 select example_sequence.nextval into :new.id from dual;
 end;
 /


--创建存储过程（调用存储过程去往用户表插入数据）
CREATE OR REPLACE PROCEDURE  hzg_procedure(userName VARCHAR2,password VARCHAR2 ,nickname VARCHAR2 ,email VARCHAR2 )
IS
BEGIN 
 insert into users values(null,userName,password,nickname,email,'user');
END;

--下面是项目所需表
create table users(
   id NUMBER(10) primary key not null,
   username varchar2(40),
   password varchar2(100),
   nickname varchar2(40),
   email varchar2(100),
   role varchar(2100)   
 );
insert into users values (1,'admin','admin','admin','admin@163.com','admin'); 
insert into users values(null,'alice','123456','al','alice@163.com','user');


create table products(
   id varchar2(100) primary key , 
   name varchar2(40), 
   price number(10,2),
   category varchar(40),
   pnum number,
   imgurl varchar2(255),
   description varchar2(255)
);


create table orders(
   id varchar2(100) primary key,
   money number(10,2),
   receiverinfo varchar2(255),
   paystate number, 
   ordertime timestamp,
   user_id number, 
   foreign key(user_id) references users(id)
);
create table orderitem(
   order_id varchar2(100),  
   product_id varchar2(100),
   buynum number, 
   primary key(order_id,product_id), 
   foreign key(order_id) references orders(id), 
   foreign key(product_id) references products(id)
);
drop sequence example_sequence
drop table products;
drop table orderitem;
drop trigger example_triger;
