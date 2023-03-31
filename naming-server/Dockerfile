FROM openjdk:18
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} naming-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/naming-server-0.0.1-SNAPSHOT.jar"]