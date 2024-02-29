drop database donation_project;
CREATE DATABASE donation_project;
USE donation_project;

CREATE TABLE role_db
(
	id int(11) not null auto_increment,
    role_name varchar(255),
    primary key (id)
);

INSERT INTO role_db(role_name)
VALUES ('Admin'),
('User');

CREATE TABLE user_db
(
	id int(11) not null auto_increment,
    full_name varchar(255),
    user_name varchar(255),
    address varchar(255),
    email varchar(255),
    phone_number varchar(255),
    user_password varchar(255),
    user_status int(11),
    note varchar(255),
    role_id int(11),
    primary key (id),
    constraint foreign key (role_id) 
    references role_db (id) 
);

INSERT INTO user_db(full_name,user_name,address,email,phone_number,user_password,user_status,note,role_id)
VALUES ('Nguyen Thi Xuan','xuan001','123 HCM','xuan001@email.com','0010010011','001123',0,'tai khoan cua Xuan',1),
('Tran Minh Tuan','tuan002','567 HN','tuan002@email.com','0020020022','123456',0,'tai khoan cua Tuan',1),
('Lam Anh Dung','dung003','255 HP','dung001@email.com','0030030033','123456',0,'tai khoan cua Dung',2),
('Ngo Van Sinh','sinh004','887 QB','sinh003@email.com','0040040044','123456',0,'tai khoan cua Sinh',2),
('Mai Minh ANh','anh003','7425 QN','anh0022@email.com','0030050033','123456',0,'tai khoan cua Minh Anh',2),
('Hoan Mai Huong','huong001','92883 HCM','huong007@email.com','0010060011','123456',0,'tai khoan cua Huong',1),
('Lai Son Nam','nam006','567 BH','nam006@email.com','0090090099','123456',0,'tai khoan cua Nam',2);


CREATE TABLE donation_db
(
	id int(11) not null auto_increment,
    donation_code varchar(255),
    donation_name varchar(255),
    donation_description varchar(255),
    money int(11),
    start_date varchar(255),
    end_date varchar(255),
	organization_name varchar(255),
    phone_number varchar(255),
    created varchar(255),
    donation_status int(11),
    primary key (id)
);

INSERT INTO donation_db(donation_code,donation_name,donation_description,money, start_date,end_date,organization_name,phone_number,created,donation_status)
VALUES ('AS1','First K','Donation 1',0,'20230121','20230330','PHAN','090010010001','567890',0),
('DO2','Second Q','Donation 2',0,'2023-03-01','2023-04-30','TRAN','7170020020002','567890',0),
('BS3','Third D','Donation 3',0,'2023-07-11','2023-09-25','PHAM','5180030030003','567890',0),
('DO4','Forth D','Donation 4',0,'2023-02-23','2023-11-07','NGO','96700200400004','567890',0),
('LQ5','Fifth L','Donation 5',0,'2023-01-18','2023-05-15','TRAN','15500505192','567890',0),
('KX2','Sixth D','Donation 6',0,'2022-11-30','2023-03-14','NAM','090007009007','567890',0),
('QS4','Seventh X','Donation 7',0,'2022-10-26','2023-06-29','PHAN','5189209220','567890',0);

CREATE TABLE user_donation_db
(
	id int(11) not null auto_increment,
    user_donation_name varchar(255),
    money int(11),
    user_donation_status int(11),
	user_donation_text varchar(255),
    created varchar(255),
    donation_id int(11),
    user_id int(11),
    primary key (id),
    constraint foreign key (user_id) 
    references user_db (id),
    constraint foreign key (donation_id) 
    references donation_db (id)
);

INSERT INTO user_donation_db(user_donation_name,money,user_donation_status,user_donation_text,created)
VALUES ('Hoang Van Chi',100000,0,'Chi chuyen khoan 100k','2023-07-30 08:10:32'),
('Ngo Quang Tai',500000,0,'Tai chuyen khoan 500k','2023-06-21 02:17:54'),
('Lam Nhat Minh',800000,0,'Minh chuyen khoan 800k','2023-02-28 12:20:22'),
('Ly Thanh Ha',300000,0,'Ha chuyen khoan 300k','2023-07-30 01:05:34'),
('Pham Thi Nhu',400000,0,'Nhu chuyen khoan 400k','2023-04-30 18:23:46');