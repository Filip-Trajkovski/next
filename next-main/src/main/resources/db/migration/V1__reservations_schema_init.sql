create schema if not exists reservations;

create table reservations.reservation_statuses
(
    id   int primary key,
    name text not null
);

insert into reservations.reservation_statuses(id, name)
values (0, 'Pending'),
       (1, 'Accepted'),
       (2, 'Rejected'),
       (3, 'Removed'),
       (4, 'Finished');

create table reservations.reservation_time_configurations
(
    id                 bigserial primary key,
    name               text    not null,
    default_config     boolean not null,
    default_start_date date
);

insert into reservations.reservation_time_configurations(name, default_config, default_start_date)
values('DEFAULT', true, null);

create table reservations.reservation_times
(
    id                                bigserial primary key,
    time_of_day                       time      not null,
    reservation_time_configuration_id bigserial not null references reservations.reservation_time_configurations (id)
);

insert into reservations.reservation_times(time_of_day, reservation_time_configuration_id)
values('17:15', (select id from reservations.reservation_time_configurations where name = 'DEFAULT')),
      ('18:50', (select id from reservations.reservation_time_configurations where name = 'DEFAULT')),
      ('20:25', (select id from reservations.reservation_time_configurations where name = 'DEFAULT')),
      ('22:00', (select id from reservations.reservation_time_configurations where name = 'DEFAULT'));

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
    id                        bigserial primary key,
    reservation_date          date,
    reservation_time_id       bigint references reservations.reservation_times (id),
    previous_reservation_date date,
    previous_reservation_time time,
    status                    int    not null references reservations.reservation_statuses (id),
    reservation_details_id    bigint not null references reservations.reservation_details (id)
);
