# Docker für UI
FROM ubuntu:16.04

MAINTAINER Mireille Puschmann

# install java
RUN apt-get update
RUN apt-get install -y openjdk-8-jre

# install apache
RUN apt-get update && apt-get install -y \
  apache2 \
  wget \
  unzip \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/*

# UI
RUN mkdir -p Users\Mireille\Docker\UI\UI
WORKDIR Users\Mireille\Docker\UI\UI
 
# use json
COPY manifest.json Users\Mireille\Docker\UI\UI
RUN npm install

COPY index.html Users\Mireille\Docker\UI\UI

COPY . Users\Mireille\Docker\UI\UI

# add entrypoint
#COPY entrypoint.sh /opt/entrypoint.sh
#RUN chmod u+x /opt/entrypoint.sh


# install openui5
ADD https://openui5.hana.ondemand.com/resources/sap-ui-core.js
RUN https://openui5.hana.ondemand.com/downloads/openui5-sdk-1.36.8.zip


# Expose port for openui5
EXPOSE 6060
EXPOSE 5055

#ENTRYPOINT ["/opt/entrypoint.sh"]


