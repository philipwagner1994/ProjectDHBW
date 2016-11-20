FROM ubuntu:16.04
MAINTAINER Philip Wagner

# install java
RUN apt-get update
RUN apt-get install -y openjdk-8-jre

# add entrypoint
COPY entrypoint.sh /opt/entrypoint.sh
RUN chmod u+x /opt/entrypoint.sh

# add takstrasse
COPY TaktstrasseOpcServer-0.0.1-SNAPSHOT.jar /

# add consumer
COPY data_collector-0.0.1-SNAPSHOT-jar-with-dependencies.jar /

# create directory for spectral file
RUN mkdir -p /temp

#expose kafka port
EXPOSE 2182

ENTRYPOINT ["/opt/entrypoint.sh"]