#!/bin/bash
# start spark
/spark/sbin/start-master.sh
sleep 5
/spark/sbin/start-slave.sh spark://0.0.0.0:7077

# set memory for spark
 cp /spark/conf/spark-defaults.conf.template /spark/conf/spark-defaults.conf
 echo "spark.driver.memory5g" >> /spark/conf/spark-defaults.conf
sleep 10

#submit to cluster
./spark/bin/spark-submit \
  --class MainController \  #da brauchen wir den Namen von unserem MainController
  --master spark://spark:7077 \
/jarFiles/SparkAnalysis.jar  #hab das File nicht gefunden in dem Repository von den anderen. Glaube das können wir so lassen... 

# endless loop
/usr/bin/tail -f /dev/null