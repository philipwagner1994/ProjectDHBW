#!/bin/bash  
cd apache-activemq-5.14.1/bin 
./activemq start

# endless loop
/usr/bin/tail -f /dev/null