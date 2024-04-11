drop database snakedb;
CREATE DATABASE IF NOT exists snakeDB;

use snakedb;

create table scores(
	id int auto_increment,
    name varchar(10) default "no name",
    score int,
    totalTime int,
	playedDate TIMESTAMP,
    primary key (id)
);
