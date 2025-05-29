#!/bin/bash

set -e

# This script will be executed inside postgres container on startup
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

    GRANT ALL ON ALL TABLES IN SCHEMA "public" to debezium;

EOSQL