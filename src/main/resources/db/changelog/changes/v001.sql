create table Test (
  id bigserial not null,
  originalUrl varchar(512) not null,
  shorteoUrl varchar(50) not null,
  primary key (id)
);