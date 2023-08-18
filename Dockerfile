FROM openjdk:8
ADD ./warehouseapp-0.0.1-snapshot.jar warehouseapp-0.0.1-snapshot.jar
ENTRYPOINT ["java", "-jar","warehouseapp-0.0.1-snapshot.jar"]
EXPOSE 8080
