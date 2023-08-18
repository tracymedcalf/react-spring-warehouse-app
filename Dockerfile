FROM openjdk:8
ADD target/warehouseapp-0.0.1-SNAPSHOT.jar warehouseapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","my-maven-docker-project.jar"]
EXPOSE 8080