-- CREATE TABLE
CREATE DATABASE Project;
USE Project;


CREATE TABLE Users(
	id int auto_increment,
	email varchar(255),
	pwd varchar(25),
	firstName nvarchar(255),
	lastName nvarchar(255),
	fullName nvarchar(255),
	userName varchar(255),
	phone varchar(11),
	id_role int,
	image varchar(255),
	
	primary key(id)
);

CREATE TABLE Role(
	id int auto_increment,
	name varchar(255),
	description text,
	
	primary key(id)
);

CREATE TABLE Project(
	id int auto_increment,
	name nvarchar(255),
	startDate date,
	endDate date,
	
	primary key(id)
);

CREATE TABLE Status(
	id int auto_increment,
	name nvarchar(255),
	
	primary key(id)
);

CREATE TABLE Job(
	id int auto_increment,
	name nvarchar(255),
	content nvarchar(255),
	startDate date,
	endDate date,
	id_project int,
	
	primary key(id)
);

CREATE TABLE Project_Users(
	id_user int,
	id_project int,
	createDate date,
	
	primary key(id_user, id_project)
);

CREATE TABLE Job_Status_Users(
	id_user int,
	id_status int,
	id_job int,
	createDate date,
	
	primary key(id_user, id_status, id_job)
);


ALTER TABLE Users 			 ADD CONSTRAINT FK_id_role_Users 				FOREIGN KEY (id_role) 		REFERENCES Role(id);
ALTER TABLE Job 			 ADD CONSTRAINT FK_id_project_Job 				FOREIGN KEY (id_project) 	REFERENCES Project(id);
ALTER TABLE Project_Users 	 ADD CONSTRAINT FK_id_user_Project_Users    	FOREIGN KEY (id_user) 		REFERENCES Users(id);
ALTER TABLE Project_Users 	 ADD CONSTRAINT FK_id_project_Project_Users 	FOREIGN KEY (id_project) 	REFERENCES Project(id);
ALTER TABLE Job_Status_Users ADD CONSTRAINT FK_id_user_Job_Status_Users    	FOREIGN KEY (id_user) 		REFERENCES Users(id);
ALTER TABLE Job_Status_Users ADD CONSTRAINT FK_id_status_Job_Status_Users  	FOREIGN KEY (id_status) 	REFERENCES Status(id);
ALTER TABLE Job_Status_Users ADD CONSTRAINT FK_id_job_Job_Status_Users     	FOREIGN KEY (id_job) 		REFERENCES Job(id);

ALTER TABLE Job ADD COLUMN id_user int;
ALTER TABLE Job ADD CONSTRAINT FK_id_user_job FOREIGN KEY (id_user) REFERENCES Users(id); 
ALTER TABLE Job ADD COLUMN id_status int;
ALTER TABLE Job ADD CONSTRAINT FK_id_status_status FOREIGN KEY (id_status) REFERENCES Status(id);

-- INSERT DATA
INSERT INTO `Role` (name, description) VALUES ('ADMIN', 'admin');
INSERT INTO `Role` (name, description) VALUES ('SUPPORTER', 'supporter');
INSERT INTO `Role` (name, description) VALUES ('DEVELOPER', 'developer');
INSERT INTO `Role` (name, description) VALUES ('SECRETARY', 'secretary');
INSERT INTO `Role` (name, description) VALUES ('TESTER', 'tester');

INSERT INTO Users (email, pwd, firstName, lastName, fullName, userName, phone, id_role) 
VALUES ('tranvana@gmail.com', '123', 'Van A', 'Tran', 'Tran Van A', 'tranvana', '123456789', 1);

INSERT INTO Project (name, startDate, endDate) VALUES ('Project A', replace("1999/08/18","/","-"), replace("2000/03/26","/","-"));
INSERT INTO Project (name, startDate, endDate) VALUES ('Project B', replace("1999/09/18","/","-"), replace("2000/04/26","/","-"));
INSERT INTO Project (name, startDate, endDate) VALUES ('Project C', replace("1999/10/18","/","-"), replace("2000/05/26","/","-"));

INSERT INTO Status (name) VALUES ('Chưa thực hiện');
INSERT INTO Status (name) VALUES ('Đang thực hiện');
INSERT INTO Status (name) VALUES ('Đã hoàn thành');

INSERT INTO Job (name, startDate, endDate, id_project, id_user, id_status)
VALUES ('Task 1', replace("1999/08/18","/","-"), replace("2000/03/26","/","-"), 2,2,1);







































