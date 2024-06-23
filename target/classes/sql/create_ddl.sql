CREATE TABLE member_test (
    num int(11),
    id int(11) ,
    pw varchar(200) ,
    age int(11) ,
    mileage int(11) ,
    phone varchar(20) ,
    constraint m_num_pk PRIMARY KEY (num)
);

insert into member_test 
values (1, 'ulla', 'ulla203', 14, 13, '010-2873-8811');

select * from member_test;