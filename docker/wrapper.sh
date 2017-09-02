#!/bin/ash

if [ -z ${DATABASE_HOST} ]; then
    echo "No database to connect to, skipping polling"
else
    echo "Trying to connect to MySQL at ${DATABASE_PORT}..."
    nc -z -v -w5 ${DATABASE_HOST} ${DATABASE_PORT}
    while [ $? -ne 0 ]; do
        sleep 10
        nc -z -v -w5 ${DATABASE_HOST} ${DATABASE_PORT}
    done
fi

if [ -z ${CONFIG_HOST} ]; then
    echo "No configuration service to connect to, skipping polling"
else
    echo "Trying to connect to configuration service at ${CONFIG_PORT}..."
    nc -z -v -w5 ${CONFIG_HOST} ${CONFIG_PORT}
    while [ $? -ne 0 ]; do
        sleep 10
        nc -z -v -w5 ${CONFIG_HOST} ${CONFIG_PORT}
    done
fi

if [ -z ${ZOOKEEPER_HOST} ]; then
    echo "No Zookeeper to connect to, skipping polling"
else
    echo "Trying to connect to Zookeeper at ${ZOOKEEPER_PORT}..."
    nc -z -v -w5 ${ZOOKEEPER_HOST} ${ZOOKEEPER_PORT}
    while [ $? -ne 0 ];  do
        sleep 10
        nc -z -v -w5 ${ZOOKEEPER_HOST} ${ZOOKEEPER_PORT}
    done
fi

if [ -z ${KAFKA_HOST} ]; then
    echo "No Kafka to connect to, skipping polling"
else
    echo "Trying to connect to Kafka at ${KAFKA_PORT}..."
    nc -z -v -w5 ${KAFKA_HOST} ${KAFKA_PORT}
    while [ $? -ne 0 ]; do
        sleep 10
        nc -z -v -w5 ${KAFKA_HOST} ${KAFKA_PORT}
    done
fi


exec java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTIONS -jar /app.jar