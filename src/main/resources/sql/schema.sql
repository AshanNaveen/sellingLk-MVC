drop
    database if exists sellingLk;
create
    database if not exists sellingLk;

use
    sellingLk;

create table user
(
    id       varchar(10) primary key,
    role     varchar(20) not null,
    email    varchar(30) not null,
    password varchar(30) not null,
    username varchar(30) not null,
    name varchar(30) not null
);

create table staff
(
    id      varchar(10) primary key,
    name    text        not null,
    email   varchar(20) not null,
    address text        not null,
    phone   varchar(15) not null,
    role    varchar(20) not null,
    uId     varchar(10) not null,
    constraint foreign key (uId) references user (id)
        on delete cascade on update cascade
);

create table salary
(
    id       varchar(10) primary key,
    otPay    double(5, 2) not null,
    basicPay double(8, 2) not null,
    empId    varchar(10)  not null,
    constraint foreign key (empId) references staff (id)
        on delete cascade on update cascade
);

create table seller
(
    id      varchar(10) primary key,
    name    varchar(25) not null,
    email   varchar(20) not null,
    address text        not null,
    phone   varchar(15) not null,
    uId     varchar(10) not null,
    constraint foreign key (uId) references user (id)
        on delete cascade on update cascade
);

create table loyalId
(
    id       varchar(10) primary key,
    discount double(3, 2) not null
);

create table buyer
(
    id      varchar(10) primary key,
    name   varchar(25) not null,
    email   varchar(20) not null,
    address text       not null,
    phone   varchar(15) not null,
    uId     varchar(10) not null,
    loyalId varchar(10) not null,
    constraint foreign key (uId) references user (id)
        on delete cascade on update cascade,
    constraint foreign key (loyalId) references loyalId (id)
        on delete cascade on update cascade

);

create table preOrder
(
    id          varchar(10) primary key,
    description text        not null,
    date        date        not null,
    buyerId     varchar(10) not null,
    constraint foreign key (buyerId) references buyer (id)
);

create table payment
(
    id       varchar(10) primary key,
    amount   double(10, 2) not null,
    date     date          not null,
    sellerId varchar(10)   not null,
    buyerId  varchar(10)   not null,
    uId      varchar(10)   not null,
    constraint foreign key (sellerId) references seller (id)
        on delete cascade on update cascade,
    constraint foreign key (buyerId) references buyer (id)
        on delete cascade on update cascade,
    constraint foreign key (uId) references user (id)
        on delete cascade on update cascade
);

create table vehicle
(
    id          varchar(10) primary key,
    description text not null,
    brand varchar(50) not null,
    model varchar(50) not null,
    year int(4) not null,
    fuelType varchar(20) not null,
    engineCapacity varchar(10) not null,
    mileage varchar(20) not null,
    price decimal not null,
    contact varchar(20) not null
);

create table sellOrder
(
    id      varchar(10) primary key,
    buyerId varchar(10) not null,
    constraint foreign key (buyerId) references buyer (id)
        on update cascade on delete cascade
);

create table sellOrderDetail
(
    date    date        not null,
    qty     int         not null,
    orderId varchar(10) not null,
    vehicleId  varchar(10) not null,
    constraint foreign key (orderId) references sellOrder (id)
        on update cascade on delete cascade,
    constraint foreign key (vehicleId) references vehicle (id)
        on update cascade on delete cascade
);

create table buyOrder
(
    id       varchar(10) primary key,
    sellerId varchar(10) not null,
    constraint foreign key (sellerId) references seller (id)
        on update cascade on delete cascade
);

create table buyOrderDetail
(
    date    date        not null,
    qty     int         not null,
    orderId varchar(10) not null,
    vehicleId  varchar(10) not null,
    constraint foreign key (orderId) references buyOrder (id)
        on update cascade on delete cascade,
    constraint foreign key (vehicleId) references vehicle (id)
        on update cascade on delete cascade
);
