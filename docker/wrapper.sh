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
        echo "Trying to connect to configuration service at ${CONFIG_PRT}..."
        sleep 10
    done
fi

java -Djava.security.egd=file:/dev/./urandom -jar /app.jar