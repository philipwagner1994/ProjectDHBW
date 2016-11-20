FROM ubuntu:16.04
MAINTAINER Mireille Puschmann

# install java
RUN apt-get update
RUN apt-get install -y openjdk-8-jre

# add entrypoint
COPY entrypoint.sh /opt/entrypoint.sh
RUN chmod u+x /opt/entrypoint.sh

RUN mkdir -p /jar
COPY ./spark-streaming-0.0.1-SNAPSHOT-jar-with-dependencies.jar /jar/jarWithDependencies.jar
RUN chmod u+x /jar/jarWithDependencies.jar


# install spark
ADD http://www-eu.apache.org/dist/spark/spark-2.0.1/spark-2.0.1-bin-hadoop2.7.tgz /spark.tgz
RUN tar -xzf /spark.tgz && mv /spark-2.0.1-bin-hadoop2.7 /spark

# Expose spark ports for master and webui
EXPOSE 7077

ENTRYPOINT ["/opt/entrypoint.sh"]
