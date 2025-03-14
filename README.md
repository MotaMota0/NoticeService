create type task_status as ENUM ('PENDING','IN_PROGRESS' ,'COMPLETED');

create type task_priority as ENUM ('HIGH','MEDIUM' ,'LOW');

create table authors(
id bigserial primary key,
username varchar(255) unique not null,
email varchar(255) not null,
pswr varchar(255) not null,
role VARCHAR(50) NOT NULL
);

create table tasks (
id BIGSERIAL primary key  ,
title VARCHAR(255) NOT NULL,
description Text NOT NULL,
status task_status not null ,
priority task_priority not null,
author_id bigint not null,
assignee_id bigint not null,

foreign key(author_id) references authors(id) on delete cascade,
foreign key(assignee_id) references authors(id) on delete set null
);


create table comments(

id bigserial primary key,
task_id bigint not null,
author_id bigint not null ,
content  text not null,
created_at  timestamp  default current_timestamp,
foreign key (task_id) references tasks(id) on delete cascade,
foreign key (author_id) references authors(id) on delete cascade

);

