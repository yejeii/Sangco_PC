drop table if exists member_test;
CREATE TABLE member_test (
    num int auto_increment,
    id varchar(20) ,
    pw varchar(20) ,
    age int ,
    phone varchar(20) ,
    constraint m_id_pk PRIMARY KEY (id)
);

insert into member_test (id, pw, age, phone)
values ('ulla', 'ulla203', 14, '010-2873-8811');

select * from member_test;