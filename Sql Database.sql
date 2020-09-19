drop database Sunrise;
create database Sunrise;
use Sunrise;
create table Vehicle(
	VehicleNumber varchar(10) not null,
	VehicleType varchar(10) not null,
	OwnerName varchar(20) not null,
	constraint primary key (VehicleNumber)
	
);
create table parkingdetails(
	VehicleNumber varchar(10) not null,
	Arrival datetime not null,
	departure datetime,
	costperhour decimal(10,2),
	parkedtime varchar(20),
	parkingfee decimal(10,2),
	paymentmethod varchar(10),
	constraint primary key (VehicleNumber,Arrival),
	constraint foreign key (VehicleNumber) references Vehicle(VehicleNumber)
	on Delete Cascade on Update Cascade
);
create table parkingslot(
	VehicleType varchar(10) not null,
	availableslot int(5),
	constraint primary key (VehicleType)
	
);
insert into parkingslot values('Car',40);
insert into parkingslot values('Van',30);
insert into parkingslot values('Bus',20);
insert into parkingslot values('Lorry',10);

insert into vehicle values('caa - 2235','car','nima');
insert into parkingdetails values('caa - 2235','2020-05-18 12:00:00','2020-05-18 12:00:00','0.0','0',0,'0');
update parkingdetails set departure = '2020-05-18 14:00:00', costperhour = '40.00' ,parkedtime = timediff(departure,Arrival) , parkingfee = timediff(departure,Arrival)/(10000)*40 , paymentmethod = 'cash' where VehicleNumber = 'caa - 2235';

select * from vehicle;
select * from parkingdetails;


select vehicle.VehicleNumber,OwnerName,VehicleType,Arrival from Vehicle,parkingdetails where vehicle.VehicleNumber=parkingdetails.VehicleNumber;

select distinct vehicle.VehicleNumber,OwnerName,VehicleType,parkingdetails.Arrival from Vehicle,parkingdetails where vehicle.vehiclenumber='car1' and parkingdetails.vehiclenumber='car1';




update parkingdetails set departure = '2020-10-20 09:00:00' ,costperhour=75.00 , parkedtime =(timediff(departure,arrival)),parkingfee=(timediff(departure,arrival)*costperhour) where vehiclenumber = 'bus1';