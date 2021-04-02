
# Useful Commands

| Command | Description | 
| -------------:|:--------:|
| `./gradlew bootRun` | Run Spring boot application |
| `./gradlew bootRun --args='--spring.profiles.active=docker'` | Run Spring boot application with 'docker' profile |
| `./gradlew applicationComposeUp'` | Start application using docker compose plugin |
| `./gradlew applicationComposeDown'` | Stop application using docker compose plugin |
| `./gradlew bootBuildImage` | Build docker image|
| `./gradlew displayDockerImageName` | Displays docker image name |
| `./gradlew displaySampleDockerCompose` | Displays sample docker compose file  |