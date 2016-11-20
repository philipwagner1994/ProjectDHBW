#!/bin/bash
# start spark
/spark/sbin/start-master.sh
sleep 5
/spark/sbin/start-slave.sh spark://0.0.0.0:7077

# set memory for spark
 cp /spark/conf/spark-defaults.conf.template /spark/conf/spark-defaults.conf
 echo "spark.driver.memory5g" >> /spark/conf/spark-defaults.conf
sleep 10

./spark/bin/spark-submit --class spark_streaming.spark_streaming.App --master local[1] /jar/jarWithDependencies.jar  

# endless loop
/usr/bin/tail -f /dev/null