FROM ubuntu:16.04
MAINTAINER Nele Ecker

# install java
RUN apt-get update
RUN apt-get install -y openjdk-8-jre

# add entrypoint
COPY entrypoint.sh /opt/entrypoint.sh
RUN chmod u+x /opt/entrypoint.sh

# install activemq
ADD http://www.apache.org/dyn/closer.cgi?filename=/activemq/5.14.1/apache-activemq-5.14.1-bin.tar.gz&action=download /activemq.tgz 
RUN tar -xzf activemq.tgz
RUN ls ./apache-activemq-5.14.1/bin 

# expose activemq ports
EXPOSE 8161
EXPOSE 61616

ENTRYPOINT ["/opt/entrypoint.sh"]


	