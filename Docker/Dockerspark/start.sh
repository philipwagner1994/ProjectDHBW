#!/bin/bash
# start spark
/spark/sbin/start-master.sh
sleep 5
/spark/sbin/start-slave.sh spark://0.0.0.0:7077
# set spark memory
# cp /spark/conf/spark-defaults.conf.template /spark/conf/spark-defaults.conf
# echo "spark.driver.memory              5g" >> /spark/conf/spark-defaults.conf
# don't exit


/usr/bin/tail -f /dev/null