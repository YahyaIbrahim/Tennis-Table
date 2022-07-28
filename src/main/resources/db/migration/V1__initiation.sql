create database  if not exists tennis;

use tennis;

create table user(
                     id int auto_increment not null PRIMARY KEY ,
                     name varchar(50) not null ,
                     email varchar(50) not null,
                     league_id int

);

create table league(
                       id int auto_increment not null primary key ,
                       win int not null DEFAULT 0,
                       lose int not null DEFAULT 0,
                       draw int not null DEFAULT 0,
                       pts int not null DEFAULT 0,
                       player_id int ,
                       foreign key (player_id) references user(id)
);

create table schedule(
                         id int auto_increment not null primary key ,
                         home_player_id int not null ,
                         away_player_id int not null ,
                         score_player_home int not null ,
                         score_player_away int not null ,
                         date date not null
);

alter table user add foreign key (league_id) references league(id);