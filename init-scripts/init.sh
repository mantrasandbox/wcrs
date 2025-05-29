#!/bin/bash
set -e

# This script will be executed inside postgres container on startup
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

    ALTER SYSTEM SET wal_level = logical;
    CREATE USER debezium WITH REPLICATION LOGIN PASSWORD 'dbz';
    GRANT ALL PRIVILEGES ON DATABASE postgres TO debezium;

    CREATE DATABASE employee;
    CREATE DATABASE notification;
    CREATE DATABASE inventory;
    CREATE DATABASE recipe;

EOSQL