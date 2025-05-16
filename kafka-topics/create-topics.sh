#!/bin/bash

# Requirements: yq (v4+) and kafka-topics.sh
BOOTSTRAP_SERVERS="kafka1:9093,kafka2:9093,kafka3:9093" #"${BOOTSTRAP_SERVERS:-kafka-bitnami:9092}"
YAML_FILE="${YAML_FILE:-/topics.yaml}"
MAX_RETRIES=5
RETRY_DELAY=5

# Function to check if Kafka is ready
check_kafka_ready() {
  local attempt=1
  while [[ $attempt -le $MAX_RETRIES ]]; do
    if kafka-topics.sh --bootstrap-server "$BOOTSTRAP_SERVERS" --list >/dev/null 2>&1; then
      echo "✅ Kafka is ready"
      return 0
    fi
    echo "⏳ Waiting for Kafka to be ready (attempt $attempt/$MAX_RETRIES)..."
    sleep $RETRY_DELAY
    ((attempt++))
  done
  echo "❌ Failed to connect to Kafka after $MAX_RETRIES attempts"
  return 1
}

# Validate YAML first
if ! yq e '.topics' "$YAML_FILE" >/dev/null 2>&1; then
  echo "❌ Invalid YAML structure in $YAML_FILE"
  exit 1
fi

# Wait for Kafka to be ready
if ! check_kafka_ready; then
  exit 1
fi

# Process each topic
yq e -o=json '.topics[]' "$YAML_FILE" | jq -c '.' | while read -r topic; do
  NAME=$(echo "$topic" | jq -r '.name')
  PARTITIONS=$(echo "$topic" | jq -r '.partitions')
  REPLICATION=$(echo "$topic" | jq -r '.replication')

  # Skip if any required field is empty
  if [[ -z "$NAME" || "$PARTITIONS" == "null" || "$REPLICATION" == "null" ]]; then
    echo "⚠️ Skipping topic due to missing required fields: $topic"
    continue
  fi

  # Convert config map to CLI format (key=value,key2=value2)
  CONFIG=$(echo "$topic" | jq -r '.config | to_entries | map("\(.key)=\(.value|tostring)") | join(",")')

  echo "➡️ Creating topic: $NAME (Partitions: $PARTITIONS, Replication: $REPLICATION)"

  echo "command used: $CONFIG "

  # Create topic with retry logic
  attempt=1
  while [[ $attempt -le $MAX_RETRIES ]]; do

    CONFIG_ARGS=""
      if [[ -n "$CONFIG" ]]; then
        IFS=',' read -ra CONFIG_ITEMS <<< "$CONFIG"
        for item in "${CONFIG_ITEMS[@]}"; do
          CONFIG_ARGS+=" --config $item"
        done
      fi

echo "Configurations: $CONFIG_ARGS"

    if kafka-topics.sh --create \
      --if-not-exists \
      --bootstrap-server "$BOOTSTRAP_SERVERS" \
      --topic "$NAME" \
      --partitions "$PARTITIONS" \
      --replication-factor "$REPLICATION" \
      $CONFIG_ARGS; then
      #${CONFIG:+--config "$CONFIG"}; then

      # Verify creation
      echo "✅ Successfully created topic $NAME"
      kafka-topics.sh --describe \
        --topic "$NAME" \
        --bootstrap-server "$BOOTSTRAP_SERVERS" | head -1
      break
    else
      echo "⚠️ Failed to create topic $NAME (attempt $attempt/$MAX_RETRIES)"
      sleep $RETRY_DELAY
      ((attempt++))
    fi
  done

  if [[ $attempt -gt $MAX_RETRIES ]]; then
    echo "❌ Failed to create topic $NAME after $MAX_RETRIES attempts"
  fi
done