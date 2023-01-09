FROM openjdk:17
VOLUME /tmp
EXPOSE 9001
ADD target/trading-service-0.0.1-SNAPSHOT.jar trading-service-0.0.1-SNAPSHOT.jar 
ENTRYPOINT ["java","-jar","/trading-service-0.0.1-SNAPSHOT.jar"]