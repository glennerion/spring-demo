This project assumes that you have a Java 8 JDK.

This project assumes that you have a recent version of docker installed and running.

#MacOS Build Instructions:

# To run the build and tests
./gradlew build

# To run the application
java -jar build/libs/spring-demo-1.0.0.jar

At this point you can go to http://localhost:8080/swagger-ui.html to play with the API.

# To run the integration tests
With the application running, in a second terminal, run the following:
cd IntegrationTests
./gradlew build

The test results will be in build/test-results/test.

# To create the docker image
docker build -t spring-demo .

