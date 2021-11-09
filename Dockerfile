FROM ubuntu:latest
RUN apt-get update 
RUN apt-get -y upgrade
RUN DEBIAN_FRONTEND=noninteractive apt-get -y install mysql-client openjdk-8-jdk mysql-server apache2
ADD target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.0.jar Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.0.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.0.jar"]