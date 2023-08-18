FROM openjdk:8
ADD ./warehouseapp-0.0.1-SNAPSHOT.jar warehouseapp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","warehouseapp-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080