#!/bin/bash
# start Taktstrasse 
java -jar /TaktstrasseOpcServer-0.0.1-SNAPSHOT.jar -amqp tcp://activemq:61616 -kafka kafka:9092 -d 1 -o /temp -topic prodData --fastforward &

#endless loop
/usr/bin/tail -f /dev/null