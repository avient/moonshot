FROM maven:3.6.3-jdk-8

ENV CHROME_PACKAGE="google-chrome-stable_83.0.4103.61-1_amd64.deb"
ENV CHROME_DRIVER_VERSION=83.0.4103.39

RUN apt-get -o Acquire::Check-Valid-Until=false -o Acquire::Check-Date=false update && \
    apt-get -y install xvfb gtk2-engines-pixbuf libxpm4 libxrender1 libgtk2.0-0 libnss3 libgconf-2-4 && \
    wget -q http://dl.google.com/linux/chrome/deb/pool/main/g/google-chrome-stable/${CHROME_PACKAGE} && \
    dpkg --unpack ${CHROME_PACKAGE} && \
    apt-get install -f -y && \
    apt-get clean && \
    rm ${CHROME_PACKAGE} && \
    rm -rf /tmp/*

RUN mkdir -p /usr/src/moonshot/

WORKDIR /usr/src/moonshot/

COPY src /usr/src/moonshot/src
COPY pom.xml /usr/src/moonshot/

CMD mvn clean install