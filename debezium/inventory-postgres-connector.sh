#!/bin/bash

curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" \
  http://debezium-connect:8083/connectors/ -d @- <<EOF
{
  "name": "inventory-postgres-connector",
  "config": {
    "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
    "plugin.name": "pgoutput",

    "database.hostname": "wcrs-db",
    "database.port": "5432",
    "database.user": "wcrs",
    "database.password": "wcrs",
    "database.dbname": "inventory",
    "database.server.name": "pg17server",

    "slot.name": "debezium_slot",
    "publication.name": "my_publication",
    "publication.autocreate.mode": "disabled",

    "topic.prefix": "pg17_inventory",
    "topic.creation.enable": "true",
	"topic.creation.default.replication.factor": -1,
	"topic.creation.default.partitions": -1,

    "table.include.list": "public.material",

    "key.converter.schemas.enable": false,
    "value.converter.schemas.enable": false,

    "database.sslmode": "disable",
    "decimal.handling.mode": "string"
  }
}
EOF