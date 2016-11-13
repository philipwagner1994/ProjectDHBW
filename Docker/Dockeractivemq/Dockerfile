FROM ubuntu:16.04

# install java
RUN apt-get update
RUN apt-get install -y openjdk-8-jre

# copy entrypoint script on docker and set permissions
COPY start.sh /opt/start.sh
RUN chmod u+x /opt/start.sh

# install activemq
ADD http://www.apache.org/dyn/closer.cgi?filename=/activemq/5.14.1/apache-activemq-5.14.1-bin.tar.gz&action=download /activemq.tgz 
RUN tar -xzf activemq.tgz
RUN ls ./apache-activemq-5.14.1/bin 

# expose port
EXPOSE 8161
EXPOSE 61616

CMD ["/opt/start.sh"]


	