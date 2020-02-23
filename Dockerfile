FROM openjdk:11
MAINTAINER romanmak@gmail.com
COPY target/Makarevich_Roman-0.0.1-SNAPSHOT.jar /opt/Makarevich.jar
ENTRYPOINT ["java","-jar","/opt/Makarevich.jar"]