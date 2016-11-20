#!/bin/bash
# start Taktstrasse 
java -jar /TaktstrasseOpcServer-0.0.1-SNAPSHOT.jar  -o /temp -d 1000 -amqp tcp://activemq:61616 -kafka kafka:9092 -topic prodData --fastforward &

sleep 20
#start consumer
java -jar /data_collector-0.0.1-SNAPSHOT-jar-with-dependencies.jar &
#endless loop
/usr/bin/tail -f /dev/null