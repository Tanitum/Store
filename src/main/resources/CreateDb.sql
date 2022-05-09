create table client
(
    id      integer not null
        constraint client_pk
            primary key autoincrement,
    name    text    not null,
    surname text    not null
);

create unique index client_id_uindex
    on client (id);

create table clientorder
(
    id        integer not null
        constraint order_pk
            primary key autoincrement,
    number    integer not null,
    status    text    not null,
    orderdate text    not null
);

create unique index order_id_uindex
    on clientorder (id);

create unique index order_number_uindex
    on clientorder (number);

create table store
(
    id   integer not null
        constraint store_pk
            primary key autoincrement,
    name text    not null
);

create table products
(
    id      integer not null
        constraint products_pk
            primary key autoincrement,
    name    text    not null,
    price   real    not null,
    storeid integer not null
        references store
);

create unique index products_id_uindex
    on products (id);

create table productsorder
(
    id        integer not null
        constraint productsorder_pk
            primary key autoincrement,
    productid integer not null
        references products,
    orderid   integer not null
        references clientorder,
    quantity  integer not null
);

create unique index productsorder_id_uindex
    on productsorder (id);

create table seller
(
    id         integer not null
        constraint seller_pk
            primary key autoincrement,
    storeid    integer not null
        references store,
    sellername text    not null,
    password   integer not null
);

create unique index seller_id_uindex
    on seller (id);

create unique index store_id_uindex
    on store (id);

create unique index store_name_uindex
    on store (name);

create table user
(
    id       integer not null
        constraint user_pk
            primary key autoincrement,
    clientid integer not null
        references client,
    username text    not null,
    password text    not null
);

create unique index user_clientid_uindex
    on user (clientid);

create unique index user_id_uindex
    on user (id);