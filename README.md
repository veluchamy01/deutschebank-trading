# Project Description
This project provides the functionality to process the trading signals for Deutsche Bank. Key Functionalities include,
  1) process trading signals - process the received trading signals and sends the request to the 3rd party - "Algo"
 
# Implementation method
The project is implemented in Java and Springboot framework. Provides REST API functionality to process the trading signals. The API receives the trading signals, processes it and sends them to the 3rd party - "Algo". The implementation is done in a loose coupling way thus making the application easier to add (or) remove the N number of new signals also in an efficent and scalable way.

# API Specification Document
  Use the below link for API specification document and try out the API calls directly in the browser
  http://localhost:9001/swagger-ui.html
  
# LOGS
Logger is configured for the application. Logs will be generated in the {$project.logs} folder. A new log file will be generated everyday and the old ones will be archieved. A new log file will also be generated whenever the log file size reaches 50 MB.

# How to RUN
The project can be run standalone by simply running the TradingApplication.java file.

# Docker File
The application is also containarized using Docker to make platform independent. Following are the steps to run the application using docker.
  1) Install Docker in the machine by refering the following link https://docs.docker.com/get-docker/
  2) build the project using "mvn clean install" command and you can see "trading-service-0.0.1-SNAPSHOT.jar" under the $project.target folder
  3) Open the terminal and navigate to the $project.target folder and use the command "docker build -t dockerimagename ." to build the docker image.
  4) Use the command "docker image ls" to verify the built image
  5) Use the command "docker run -p9001:9001 dockerimagename" to run the application in Docker container.

# Deployment YML File
The application can also be deployed in the kubernetes cluster. 
The "$project.trading-service-deployment.yml" file is configured to host the application in kubernetes cluster.
