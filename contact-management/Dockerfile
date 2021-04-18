FROM adoptopenjdk/openjdk11:jdk-11.0.10_9
#RUN addgroup -S springdocker && adduser -S springdocker -G springdocker
#USER springdocker:springdocker
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
COPY target/contact-management-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]