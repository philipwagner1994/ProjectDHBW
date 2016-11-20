#!/bin/bash

echo "advertised.host.name=kafka" >> /kafka/config/server.properties

# start kafka
/kafka/bin/zookeeper-server-start.sh /kafka/config/zookeeper.properties &
# waiting to give zookeeper time to start
sleep 5

/kafka/bin/kafka-server-start.sh /kafka/config/server.properties &
sleep 5

# create topic
# https://kafka.apache.org/quickstart

 echo "------------------------------------------------"
 echo "-----------------TOPIC--------------------------"
/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic prodData #Error: NoNode
 echo "------------------------------------------------"
 echo "-----------------TOPIC--------------------------"
/kafka/bin/kafka-topics.sh --list --zookeeper localhost:2181

# endless loop
/usr/bin/tail -f /dev/null