# # Use the official Maven image to create a build artifact.
# FROM maven:3.8.4-openjdk-17-slim AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package
# # Using Maven local repo as a Docker volume
# VOLUME /root/.m2
# RUN mvn -f /home/app/pom.xml clean package
# # Use OpenJDK for running the application.
# FROM openjdk:17-slim
# COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar
# ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]


# Use the official Maven image to create a build artifact.
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /home/app
COPY . .
RUN mvn clean package

# Using Maven local repo as a Docker volume
VOLUME /root/.m2

# Use OpenJDK for running the application.
FROM openjdk:17-slim
COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
