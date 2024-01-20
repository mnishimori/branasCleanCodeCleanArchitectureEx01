create schema if not exists "account_management";

create table if not exists "account_management"."account"
(
    "id"            uuid                    not null default gen_random_uuid() primary key,
    "email"         varchar(500)            not null,
    "name"          varchar(500)            not null,
    "cpf"           varchar(14)             not null,
    "car_plate"     varchar(10)             null,
    "is_passenger"  boolean                 not null,
    "is_driver"     boolean                 not null
);

create index if not exists account_email_idx ON account_management.account using btree (email);
create index if not exists account_cpf_idx ON account_management.account using btree (cpf);