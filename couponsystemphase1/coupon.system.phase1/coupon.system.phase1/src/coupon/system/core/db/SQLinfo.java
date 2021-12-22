package coupon.system.core.db;

public class SQLinfo {
	
	// this is the table for SQL so you can RUN the project
	
	/*
	create schema coupon_sys_db;

	create table company(
	id int primary key auto_increment,
	`name` varchar (30), 
	`email` varchar (30),
	`password` varchar (30));

	create table customer(
	id int primary key auto_increment,
	`first_name` varchar (30), 
	`last_name` varchar (30), 
	`email` varchar (30),
	`password` varchar (30));

	create table category(
	id int primary key,
	`name` varchar (30)
	);
	insert into category values(0,'DRINKS');
	insert into category values(1,'FRUITS');
	insert into category values(2,'VEGETABLES' );
	insert into category values(3,'FROZEN');
	insert into category values(4,'HOME_CLOTHING');
	insert into category values(5,'ELECTRICITY');

	create table coupon(
	id int primary key auto_increment,
	`company_id` int  references `company`(`id`),
	`category_id` int references `category`(`id`),
	`title` varchar (50),
	`description` varchar (360),
	`start_date` date,
	`end_date` date,
	`amount` int,
	`price` double,
	`image` varchar (30)
	);

	create table customer_vs_coupon(
	`customer_id` int,
	`coupon_id` int, 
	primary key(`customer_id`, `coupon_id`),
	foreign key (`customer_id`) references customer(`id`),
	foreign key (`coupon_id`) references coupon(`id`)
	);
*/



}
