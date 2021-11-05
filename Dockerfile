FROM  openjdk:8-jdk-alpine
EXPOSE 8082
ADD /target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.war Timesheet.war
ENTRYPOINT ["java","-jar", "Timesheet.war"]