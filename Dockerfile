FROM sdorra/oracle-java-8

EXPOSE 8080

COPY build/libs/spring-demo-1.0.0.jar /deployments/spring-demo-1.0.0.jar

CMD java -jar /deployments/spring-demo-1.0.0.jar