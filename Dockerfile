### STAGE 1: Build ###
FROM openjdk:8-alpine
ADD target/opportunites-MS.jar opportunites-MS.jar
ENTRYPOINT ["java","-jar","/opportunites-MS.jar"]
