
# Useful Commands

| Command | Description | 
| -------------:|:--------:|
| `./gradlew bootRun` | Run Spring boot application |
| `./gradlew bootRun --args='--spring.profiles.active=docker'` | Run Spring boot application with 'docker' profile |
| `./gradlew bootBuildImage` | Build docker image using spring boot plugin|
| `./gradlew bootAppComposeUp'` | Start application using docker compose plugin |
| `./gradlew bootAppComposeDown'` | Stop application using docker compose plugin |
| `./gradlew jibDockerBuild` | Build docker image using jib |
| `./gradlew displayDockerImageName` | Displays docker image name |
| `./gradlew displaySampleDockerCompose` | Displays sample docker compose file  |