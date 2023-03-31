FROM openjdk:18
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} currency-conversion-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/currency-conversion-service-0.0.1-SNAPSHOT.jar"]