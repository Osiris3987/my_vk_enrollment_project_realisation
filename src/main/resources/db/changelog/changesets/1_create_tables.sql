create table accounts
(
    id       bigserial primary key,
    name     varchar(255)  not null,
    username varchar(255)  not null unique,
    email    varchar(255) not null unique,
    password varchar(255)  not null
);

create table if not exists accounts_roles
(
    account_id bigint       not null,
    role    varchar(255) not null,
    primary key (account_id, role),
    constraint fk_accounts_roles_accounts foreign key (account_id) references accounts (id) on delete cascade on update no action
);
