#!/usr/bin/env bash
PGPASSWORD=postgres psql -U postgres
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'next_db';
drop database next_db;
create database next_db;
\q
