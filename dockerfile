# We are grabbing our base image that has Java JRE installed
from openjdk:8-jre-alpine

# We are coping the .jar from target folder and pasting it on our image named as app.jar
copy target/*.jar app.jar

# we are telling how to run this application via CLI commands
# java uses the java cli
# -jar flag indicates that this will be a .jar file
# app.jar is the file name we want to run
entrypoint ["java","-jar","app.jar"]