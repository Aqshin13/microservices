FROM openjdk:18
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/api-gateway-0.0.1-SNAPSHOT.jar"]