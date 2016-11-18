FROM ubuntu:16.04
MAINTAINER Mireille Puschmann

RUN apt-get update
RUN apt-get install -y nodejs
RUN apt-get install -y npm
RUN ln -s /usr/bin/nodejs /usr/bin/node

RUN npm install -g http-server

ADD UI/WebContent/index.html /opt/UI/WebContent/index.html
WORKDIR /opt/UI/WebContent

CMD ["http-server", "-s"]