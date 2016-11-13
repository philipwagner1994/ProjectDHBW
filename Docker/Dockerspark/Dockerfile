FROM ubuntu:16.04


# install java
RUN apt-get update
RUN apt-get install -y openjdk-8-jre

# add entrypoint script
COPY start.sh /
RUN chmod u+x /start.sh

# install spark
ADD http://www-eu.apache.org/dist/spark/spark-2.0.1/spark-2.0.1-bin-hadoop2.7.tgz /spark.tgz
RUN tar -xzf /spark.tgz && mv /spark-2.0.1-bin-hadoop2.7 /spark

# Expose spark webui and master port
EXPOSE 8080
EXPOSE 7077

ENTRYPOINT ["/start.sh"]
#CMD