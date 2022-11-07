use furama_minhduc;

INSERT INTO position (name)
VALUE ('Quản lý'), 
('Nhân viên');

INSERT INTO education_degree (name)
VALUE ('Trung cấp'),
('Cao Đẳng'), 
('Đại học'), 
('Sau đại học');

INSERT INTO division (name)
VALUE ('Sale - Marketing'),
('Hành chính'),
('PHỤc vụ'),
('Quản lý');

INSERT INTO rent_type (name) 
VALUE ('year'),
   ('month'),
   ('day'),
   ('hour');
    
INSERT INTO facility_type(name)
VALUE ('Villa'),
    ('House'),
    ('Room');

INSERT INTO facility ( `name`,`area`, `cost`,`max_people`,`standard_room`, `description_other_convenience`,`pool_area`,`number_of_floors`,`facility_free`,`rent_type`,`facility_type`) 
VALUE ('Villa Beach Front',25000,1000000,10,'vip','Có hồ bơi',500,4,null,3,1),
     ('House Princess 01',14000,5000000,7,'vip','Có thêm bếp nướng',null,3,null,2,2),
     ('Room Twin 01',5000,1000000,2,'normal','Có tivi',null,null,'1 Xe máy, 1 Xe đạp',4,3),
     ('Villa No Beach Front',22000,9000000,8,'normal','Có hồ bơi',300,3,null,3,1),
     ('House Princess 02',10000,4000000,5,'normal','Có thêm bếp nướng',null,2,null,3,2),
     ('Room Twin 02',3000,900000,2,'normal','Có tivi',null,null,'1 Xe máy',4,3);

INSERT INTO `furama_minhduc`.`employee` (`name`,`date_of_birth`,`id_card`,`salary`,`phone_number`,`email`,`address`,`position_id`,`education_degree_id`,`division_id`, `user_name`) 
VALUE ('Nguyễn Văn Anh','1970-11-07', '456231786',10000000,'0901234121','annguyen@gMAIL.COM','295 Nguyễn Tất Thành, Đà Nẵng',1,3,1,'a'),
 ('Lê Văn Bình','1997-04-09', '654231234',7000000,'0934212314','binhlv@gmaIl.com','22 YÊN BÁI, Đà Nẵng',1,2,2,'a'),
 ('Hồ Thị Yến','1995-12-12', '999231723',14000000,'0412352315','thiyen@gMail.com','K234/11 Điện BiÊN PHỦ, GIa Lai',1,3,2,'a'),
 ('Võ Công Toản','1980-04-04', '123231365',17000000,'0374443232','toan0404@gmail.com','77 Hoàng DIệu, Quảng TrỊ',1,4,4,'a'),
 ('Nguyễn BỈnh Phát','1999-12-09', '454363232',6000000,'0902341231','PHATPhaT@gmail.com','43 Yên Bái, Đà Nẵng',2,1,1,'a'),
 ('Khúc NGUYỄN An Nghi','2000-11-08', '964542311',7000000,'0978653213','annghI20@GMAIl.Com','294 Nguyễn Tất ThànH, Đà Nẵng',2,3,3,'a'),
 ('NguYỄN HỮU Hà','1993-01-01', '534323231',8000000,'0941234553','nhh0101@gmail.com','4 NGUYỄN CHí Thanh, Huế',2,3,2,'a'),
 ('Nguyễn Hà ĐÔng','1989-09-03', '232414123',9000000,'0642123111','dOnghanguyen@gmail.com','111 HùnG VƯƠNG, HÀ Nội',2,4,4,'a'),
 ('Tòng Hoang','1982-09-03', '256781231',6000000,'0245144444','hoangtong@gmail.Com','213 Hàm Nghi, Đà Nẵng',2,4,4,'a'),
 ('NGuyễn Công Đạo','1994-01-08', '755434343',8000000,'0988767111','nguyencongdao@gmail.com','6 Hoà Khánh, Đồng Nai',2,3,2,'a');

INSERT INTO attach_facility (name,cost, unit,status)
VALUE ('Karaoke',10000,'Giờ','Tiện nghi, hiện đại'),
      ('Thuê xe máy',10000,'chiếc','Hỏng 1 xe'),
      ('Thuê xe đạp',20000,'chiếc','Tốt'),
      ('Buffet buổi sáng',15000,'suất','Đầy đủ đồ ăn, tráng miệng'),
      ('Buffet buổi trưa',90000,'suất','Đầy đủ đồ ăn, tráng miệng'),
      ('Buffet buổi tối',16000,'suất','Đầy đủ đồ ăn, tráng miệng');
 
INSERT INTO `furama_minhduc`.`contract` ( `start_date`,`end_date`,`deposit`,`employee_id`,`customer_id`,`facility_id`) 
VALUE ('2020-12-08','2020-12-08',0,3,1,3),
      ('2020-07-14','2020-07-21',200000,7,3,1),
      ('2021-03-15','2021-03-17',50000,3,4,2),
      ('2021-01-14','2021-01-18',100000,7,5,5),
      ('2021-07-14','2021-07-15',0,7,2,6),
      ('2021-06-01','2021-06-03',0,7,7,6),
      ('2021-09-02','2021-09-05',100000,7,4,4),
      ('2021-06-17','2021-06-18',150000,3,4,1),
      ('2020-11-19','2020-11-19',0,3,4,3),
      ('2020-04-12','2020-04-14',0,10,3,5),
	  ('2021-04-25','2021-04-25',0,2,2,1),
      ('2021-05-25','2021-05-27',0,7,10,1);
     SELECT * FROM furama_minhduc.contract_detail;
 INSERT INTO `furama_minhduc`.`contract_detail` ( `quantity`, `attach_facility_id`, `contract_id`) 
VALUE (5,2,4),
 (8,2,5),
 (15,2,6),
 (1,3,1),
 (11,3,2),
 (1,1,3),
 (2,1,2),
 (2,12,2);
