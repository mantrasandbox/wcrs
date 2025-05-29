#!/bin/bash
set -e

# This script will be executed inside postgres container on startup
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

    -- Required settings
    ALTER SYSTEM SET wal_level = logical;
    ALTER SYSTEM SET max_replication_slots = 10;
    ALTER SYSTEM SET max_wal_senders = 10;
    ALTER SYSTEM SET wal_keep_size = 512; -- or higher depending on your load

    CREATE USER debezium WITH REPLICATION PASSWORD 'dbz' LOGIN;

    CREATE DATABASE employee;
    CREATE DATABASE notification;
    CREATE DATABASE inventory;
    CREATE DATABASE recipe;

    GRANT ALL PRIVILEGES ON DATABASE inventory TO debezium;
    GRANT ALL PRIVILEGES ON DATABASE recipe TO debezium;

    GRANT ALL ON ALL TABLES IN SCHEMA "public" to debezium;

EOSQL