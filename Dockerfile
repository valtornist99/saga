FROM java:8
ADD target/choreography-orchestrator.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]