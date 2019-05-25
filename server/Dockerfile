# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Diana Silva <1181702@isep.ipp.pt>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 9000 available to the world outside this container
EXPOSE 9000

# The application's jar file
#ARG JAR_FILE=build/lib/spring-boot-vue-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY build/libs/spring-boot-vue-0.0.1-SNAPSHOT.jar /home/spring-boot-vue-0.0.1-SNAPSHOT.jar

# Run the jar file 
CMD ["java","-jar","/home/spring-boot-vue-0.0.1-SNAPSHOT.jar"]