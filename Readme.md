# Dockerize your Spring Boot application - Useful plugins and techniques to build efficient docker image

This repository contains sample setup for building optimised docker image and shows some useful plugins and techniques.

## Features

* Efficient docker image using **Layered Jars**.
* **Multi-stage Docker** build.
* **Google’s Distroless** base image with healthchecks without `bash` or `curl`
* Configuring **Non-root user** in docker image.
* **Cloud native buildpacks** - with spring boot gradle plugin.
* **Jib** gradle plugin.
* Using **docker-compose** with gradle.

## Getting Started

### Prerequisite

* Java 11
* Docker

### Installation

|Step| Custom Image | Cloud native Image| Jib Image |
| -------------:|:--------:|:--------:|:--------:|
| Build image | `docker build -t spring-docker-setup-healthcheck:0.0.1-SNAPSHOT .` |`./gradlew bootBuildImage` |`./gradlew jibDockerBuild` | 
| Start docker-compose |  `./gradlew healthcheckComposeUp` | `./gradlew bootAppComposeUp` |`./gradlew jibComposeUp` | 
| Stop docker-compose |  `./gradlew healthcheckComposeDown` | `./gradlew bootAppComposeDown` |`./gradlew jibComposeUp` |

### Usage

* After successful installation, check if all applications are running `docker ps` e.g.
    ```shell
      docker ps
  
      CONTAINER ID   IMAGE                                                         COMMAND                  CREATED              STATUS                        PORTS                     NAMES
      2eab466c947d   spring-docker-setup-jib-plugin:0.0.1-SNAPSHOT                 "java -cp /app/resou…"   7 seconds ago        Up 7 seconds                  0.0.0.0:53637->8080/tcp   spring-docker-setup-jib
      9cd791a65fef   spring-docker-setup-healthcheck:0.0.1-SNAPSHOT                "java org.springfram…"   About a minute ago   Up About a minute (healthy)   0.0.0.0:53551->8080/tcp   spring-docker-setup-healthcheck
      0bfcde6a2f2e   com.rbiedrawa.docker.app/spring-docker-setup:0.0.1-SNAPSHOT   "/cnb/process/web"       2 minutes ago        Up 2 minutes                  0.0.0.0:8080->8080/tcp    spring-docker-setup
    ```
  **** Notice healthy status in our `custom` image.

* Next investigate how spring packaged the jars.
  ```shell
   cd ./build/libs
  
   # Extract jar
  java -Djarmode=layertools -jar spring-docker-setup-0.0.1-SNAPSHOT.jar extract
  ```
  
* Checkout created directories(application, dependencies, snapshot-dependencies and spring-boot-loader) 

## Other Useful Commands

| Command | Description | 
| -------------:|:--------:|
| `./gradlew tasks` | Display all available tasks |
| `./gradlew bootRun` | Run Spring boot application |
| `./gradlew bootRun --args='--spring.profiles.active=docker'` | Run Spring boot application with 'docker' profile |
| `./gradlew displayDockerImageName` | Displays docker image name |
| `./gradlew displaySampleDockerCompose` | Displays sample docker compose file  |
| `./gradlew bootBuildImage` | Build docker image using spring boot plugin|
| `./gradlew bootAppComposeUp` | Start application using docker compose plugin |
| `./gradlew bootAppComposeDown` | Stop application using docker compose plugin |
| `./gradlew jibDockerBuild` | Build docker image using jib |

## References

* [Production-grade Spring Boot Docker images](https://pete-woods.com/2019/02/production-grade-spring-boot-docker-images/)
* [Creating Efficient Docker Images with Spring Boot 2.3](https://spring.io/blog/2020/08/14/creating-efficient-docker-images-with-spring-boot-2-3)
* [Jib plugin](https://github.com/GoogleContainerTools/jib)
* [Gradle docker-compose plugin](https://github.com/avast/gradle-docker-compose-plugin)

## License

Distributed under the MIT License. See `LICENSE` for more information.
