FROM ubuntu:16.04
MAINTAINER Philip Wagner <philip.wagner@sap.com>

# install java
RUN apt-get update
RUN apt-get install -y openjdk-8-jre

# add entrypoint
COPY entrypoint.sh /opt/entrypoint.sh
RUN chmod u+x /opt/entrypoint.sh

#install kafka
#ADD http://mirror.symnds.com/software/Apache/kafka/0.10.0.1/kafka_2.11-0.10.0.1.tgz /kafka.tgz
ADD http://mirrors.ae-online.de/apache/kafka/0.10.0.1/kafka_2.11-0.10.0.1.tgz /kafka.tgz
RUN tar -xzf /kafka.tgz && mv /kafka_2.11-0.10.0.1 /kafka

# expose kafka port
EXPOSE 2181
EXPOSE 9092


ENTRYPOINT ["/opt/entrypoint.sh"]