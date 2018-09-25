# Spring Boot vs. Vert.x Benchmarks

### Description

The aim of this repository is to test the startup performance between Spring Boot and Vert.x using different configurations

### Build 

./gradlew clean build ShadowJar

### Run Tests

```
$ cd scripts/
$ ./bootstrap-service.sh <GROUP_ID> <VERSION> [useJMC]
   Examples:
     ./bootstrap-service.sh vanilla-vertx 0.0.1-SNAPSHOT true
     ./bootstrap-service.sh vanilla-vertx 0.0.1-SNAPSHOT
     ./bootstrap-service.sh vanilla-spring-boot 0.0.1-SNAPSHOT true
     ./bootstrap-service.sh vanilla-spring-boot 0.0.1-SNAPSHOT
```