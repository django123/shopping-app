create sequence tokens_seq
    increment by 50;

alter sequence tokens_seq owner to postgres;

create table _user
(
    account_locked boolean      not null,
    enabled        boolean      not null,
    created_at     timestamp(6) not null,
    created_by     bigint       not null,
    id             bigserial
        primary key,
    updated_at     timestamp(6),
    updated_by     bigint,
    email          varchar(255)
        unique,
    firstname      varchar(255),
    lastname       varchar(255),
    password       varchar(255)
);

alter table _user
    owner to postgres;

create table roles
(
    created_at timestamp(6) not null,
    created_by bigint       not null,
    id         bigserial
        primary key,
    updated_at timestamp(6),
    updated_by bigint,
    name       varchar(255)
        unique
);

alter table roles
    owner to postgres;

create table _user_roles
(
    roles_id bigint not null
        constraint fkhe8gpt820agumjt0o147p379x
            references roles,
    user_id  bigint not null
        constraint fk1knb08qasyc3njr6m6je05u4f
            references _user
);

alter table _user_roles
    owner to postgres;

create table shares
(
    created_at  timestamp(6) not null,
    created_by  bigint       not null,
    id          bigserial
        primary key,
    share_id    bigint,
    shopping_id bigint,
    updated_at  timestamp(6),
    updated_by  bigint
);

alter table shares
    owner to postgres;

create table shoppings
(
    archived   boolean,
    shared     boolean,
    statut     boolean,
    created_at timestamp(6) not null,
    created_by bigint       not null,
    id         bigserial
        primary key,
    local_date timestamp(6),
    updated_at timestamp(6),
    updated_by bigint,
    comment    varchar(255),
    name       varchar(255),
    saver_name varchar(255)
);

alter table shoppings
    owner to postgres;

create table shopping_user
(
    id      bigint not null
        constraint fkdpjnxvoktvcco6685q25n5tdq
            references _user,
    shop_id bigint not null
        constraint fkmwnxu5qw01gi61aq5g0an1m4x
            references shoppings
);

alter table shopping_user
    owner to postgres;

create table tasks
(
    status      boolean      not null,
    created_at  timestamp(6) not null,
    created_by  bigint       not null,
    id          bigserial
        primary key,
    shopping_id bigint       not null
        constraint fkgv7mwghmw1xqbl1hgpsrvyh7u
            references shoppings,
    updated_at  timestamp(6),
    updated_by  bigint,
    description varchar(255),
    name        varchar(255) not null
);

alter table tasks
    owner to postgres;

create table tokens
(
    id           integer not null
        primary key,
    created_at   timestamp(6),
    expires_at   timestamp(6),
    user_id      bigint  not null
        constraint fkj36vsu89j0rxnc651rqdxgw3o
            references _user,
    validated_at timestamp(6),
    token        varchar(255)
        unique
);

alter table tokens
    owner to postgres;

