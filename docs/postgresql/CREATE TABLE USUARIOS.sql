create table users(
	username character varying(50) not null primary key,
	password character varying(500) not null,
	enabled boolean not null
);
--Armazenar Senha com BCrypt

create table authorities (
	username character varying(50) not null,
	authority character varying(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);