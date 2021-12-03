FROM openjdk

WORKDIR /app

COPY target/list-0.0.1-SNAPSHOT.jar /app/list.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "list.jar"]