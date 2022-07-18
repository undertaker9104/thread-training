drop database if exists s0;
create database s0 default charset utf8;
use s0;
drop table if exists `t_user`;
create table `t_user` (
    `id` bigint not null AUTO_INCREMENT,
    `name` varchar(20) not null,
    `uname` varchar(20) not null,
    `pwd` char(64) not null,
    `salt` char(8),
    `tel` char(20),
    `address` varchar(50) default null,
    `avatar` varchar(100) default null,
    `ip` int default 0,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    primary key(`id`)
);

drop table if exists `t_post0`;
create table t_post0 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post1`;
create table t_post1 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post2`;
create table t_post2 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post3`;
create table t_post3 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);

drop database if exists s1;
create database s1 default charset utf8;
use s1;
drop table if exists `t_user`;
create table `t_user` (
    `id` bigint not null AUTO_INCREMENT,
    `name` varchar(20) not null,
    `uname` varchar(20) not null,
    `pwd` char(64) not null,
    `salt` char(8),
    `tel` char(20),
    `address` varchar(50) default null,
    `avatar` varchar(100) default null,
    `ip` int default 0,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    primary key(`id`)
);

drop table if exists `t_post0`;
create table t_post0 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);

drop table if exists `t_post1`;
create table t_post1 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post2`;
create table t_post2 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post3`;
create table t_post3 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);



drop database if exists s2;
create database s2 default charset utf8;
use s2;
drop table if exists `t_user`;
create table `t_user` (
    `id` bigint not null AUTO_INCREMENT,
    `name` varchar(20) not null,
    `uname` varchar(20) not null,
    `pwd` char(64) not null,
    `salt` char(8),
    `tel` char(20),
    `address` varchar(50) default null,
    `avatar` varchar(100) default null,
    `ip` int default 0,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    primary key(`id`)
);

drop table if exists `t_post0`;
create table t_post0 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post1`;
create table t_post1 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post2`;
create table t_post2 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);



drop table if exists `t_post3`;
create table t_post3 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);



drop database if exists s3;
create database s3 default charset utf8;
use s3;
drop table if exists `t_user`;
create table `t_user` (
    `id` bigint not null AUTO_INCREMENT,
    `name` varchar(20) not null,
    `uname` varchar(20) not null,
    `pwd` char(64) not null,
    `salt` char(8),
    `tel` char(20),
    `address` varchar(50) default null,
    `avatar` varchar(100) default null,
    `ip` int default 0,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    primary key(`id`)
);

drop table if exists `t_post0`;
create table t_post0 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post1`;
create table t_post1 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post2`;
create table t_post2 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);


drop table if exists `t_post3`;
create table t_post3 (
    `id` bigint not null AUTO_INCREMENT,
    `user_id` bigint not null,
    `name` varchar(20) not null,
    `created` datetime default current_timestamp,
    `modified` datetime default current_timestamp,
    `title` char(12) not null,
    `avatar` varchar(100) default null,
    `approve` int default 0,
    `dislike` int default 0,
    `state` tinyint default 0,
    primary key(id),
    KEY `idx_userid` (`user_id`)
);