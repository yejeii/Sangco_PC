drop table if exists MEMBER;
CREATE TABLE MEMBER (
    num int auto_increment,
    id varchar(20) ,
    pw varchar(20) ,
    age int ,
    phone varchar(20) ,
    constraint m_id_pk PRIMARY KEY (id)
);

insert into MEMBER (id, pw, age, phone)
values ('ulla', 'ulla203', 14, '010-2873-8811');

select * from MEMBER;