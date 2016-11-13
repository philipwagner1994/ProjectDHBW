#!/bin/bash

echo "advertised.host.name=kafka" >> /kafka/config/server.properties

# start kafka
/kafka/bin/zookeeper-server-start.sh /kafka/config/zookeeper.properties &
# waiting to give zookeeper time to start
sleep 10

/kafka/bin/kafka-server-start.sh /kafka/config/server.properties &
sleep 10

# create topic
# https://kafka.apache.org/quickstart
/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic prodData

# endless loop
/usr/bin/tail -f /dev/null