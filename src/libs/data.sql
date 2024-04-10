drop database snakedb;
CREATE DATABASE IF NOT exists snakedb;

use snakedb;

create table jugador(
	id int auto_increment,
    name varchar(50) default "no name",
    score int,
    totaltime float,
	playedDate DATE,
    primary key (id)
);

-- datos de prueba 
INSERT INTO jugador(score, totaltime, playedDate) VALUES (12, 60.0, "2024-04-09");
INSERT INTO jugador(score, totaltime, playedDate) VALUES (1, 10.0, "2024-04-09");
INSERT INTO jugador(score, totaltime, playedDate) VALUES (5, 30.0, "2024-04-09");
INSERT INTO jugador(score, totaltime, playedDate) VALUES (14, 65.0, "2024-04-09");
INSERT INTO jugador(score, totaltime, playedDate) VALUES (2, 20.0, "2024-04-09");

SELECT * FROM jugador;

delete from jugador where id = 9;