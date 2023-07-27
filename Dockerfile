FROM openjdk:17-jdk-alpine
COPY target/Movie-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-jar","/app.war"]