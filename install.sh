#!/bin/bash
mvn -f config-service/pom.xml clean package docker:build &&
    mvn -f discovery-service/pom.xml clean package docker:build &&
    mvn -f gateway-service/pom.xml clean package docker:build &&
    mvn -f uaa-service/pom.xml clean package docker:build &&
    mvn -f profile-service/pom.xml clean package docker:build &&
    mvn -f blog-service/pom.xml clean package docker:build
