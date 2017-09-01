#!/bin/bash

if [ -z ${DATABASE_HOST} ]; then
    echo "No database to connect to, skipping polling"
else
    while ! exec 6<>/dev/tcp/${DATABASE_HOST}/${DATABASE_PORT}; do
        echo "Trying to connect to MySQL at ${DATABASE_PORT}..."
        sleep 10
    done
fi

if [ -z ${CONFIG_HOST} ]; then
    echo "No configuration service to connect to, skipping polling"
else
    while ! exec 6<>/dev/tcp/${CONFIG_HOST}/${CONFIG_PORT}; do
        echo "Trying to connect to configuration service at ${CONFIG_PORT}..."
        sleep 10
    done
fi

if [ -z ${ZOOKEEPER_HOST} ]; then
    echo "No Zookeeper to connect to, skipping polling"
else
    while ! exec 6<>/dev/tcp/${ZOOKEEPER_HOST}/${ZOOKEEPER_PORT}; do
        echo "Trying to connect to Zookeeper at ${ZOOKEEPER_PORT}..."
        sleep 10
    done
fi

if [ -z ${KAFKA_HOST} ]; then
    echo "No Kafka to connect to, skipping polling"
else
    while ! exec 6<>/dev/tcp/${KAFKA_HOST}/${KAFKA_PORT}; do
        echo "Trying to connect to Kafka at ${KAFKA_PORT}..."
        sleep 10
    done
fi

java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTIONS -jar /app.jar