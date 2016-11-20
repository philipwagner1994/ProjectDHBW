FROM ubuntu:16.04
MAINTAINER Mireille Puschmann

#install nodejs, npm, wget und unzip
RUN apt-get update && apt-get install -y nodejs && apt-get install -y npm && apt-get install -y wget && apt-get install -y unzip
RUN ln -s /usr/bin/nodejs /usr/bin/node

#download openui5 sdk
RUN wget https://openui5.hana.ondemand.com/downloads/openui5-sdk-1.40.10.zip -P /var/www/html
RUN unzip -o /var/www/html/openui5-sdk-1.40.10.zip -d /var/www/html/
RUN rm /var/www/html/openui5-sdk-1.40.10.zip

RUN npm install -g http-server

#copy UI Content
COPY UI/WebContent/index.html /opt/UI/WebContent/index.html
COPY UI /opt/UI
COPY UI/WebContent /opt/UI/WebContent
COPY UI/.settings /opt/UI/.settings
COPY UI/.classpath /opt/UI/.classpath
COPY UI/.project /opt/UI/.project
COPY UI/WebContent /opt/UI/WebContent
COPY UI/WebContent/control /opt/UI/WebContent/control
COPY UI/WebContent/img /opt/UI/WebContent/img
COPY UI/WebContent/json /opt/UI/WebContent/json
COPY UI/WebContent/META-INF /opt/UI/WebContent/META-INF
COPY UI/WebContent/taktstrasse /opt/UI/WebContent/taktstrasse
COPY UI/WebContent/WEB-INF /opt/UI/WebContent/WEB-INF
COPY UI/WebContent/Component.js /opt/UI/WebContent/Component.js
COPY UI/WebContent/manifest.json /opt/UI/WebContent/manifest.json
COPY UI/WebContent/lib /opt/UI/WebContent/lib
COPY UI/WebContent/css /opt/UI/WebContent/css

WORKDIR /opt/UI/WebContent/

EXPOSE 8080

#start webserver
CMD ["http-server", "-s"]