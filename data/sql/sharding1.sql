use s0;

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

use s1;

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



use s2;

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



use s3;

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