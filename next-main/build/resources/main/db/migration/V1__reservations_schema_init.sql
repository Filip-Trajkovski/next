create schema if not exists reservations;

create table reservations.reservation_statuses
(
    id   int primary key,
    name text not null
);

insert into reservations.reservation_statuses(id, name)
values (0, 'Pending'),
       (1, 'Accepted'),
       (2, 'Rejected');

create table reservations.reservation_time_configurations
(
    id                 bigserial primary key,
    name               text    not null,
    default_config     boolean not null,
    default_start_date date
);

create table reservations.reservation_times
(
    id                                bigserial primary key,
    time_of_day                       time      not null,
    reservation_time_configuration_id bigserial not null references reservations.reservation_time_configurations (id)
);

create table reservations.alternative_reservation_time_date_ranges
(
    id                                bigserial primary key,
    from_date                         date   not null,
    to_date                           date   not null,
    reservation_time_configuration_id bigint not null references reservations.reservation_time_configurations (id)
);


create table reservations.reservation_details
(
    id                bigserial primary key,
    full_name         text not null,
    email             text not null,
    phone_number      text not null,
    comment           text,
    number_of_players int  not null
);

create table reservations.reservations
(
    id                           bigserial primary key,
    reservation_date             date,
    reservation_time_id          bigint references reservations.reservation_times (id),
    previous_reservation_date    date,
    previous_reservation_time_id time,
    status                       int    not null references reservations.reservation_statuses (id),
    reservation_details_id       bigint not null references reservations.reservation_details (id)
);
