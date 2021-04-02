FROM adoptopenjdk:11-jre-hotspot as java-builder
#TODO: maybe './gradlew build -x test' phase
WORKDIR application
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

RUN adduser --system --home /var/cache/bootapp --shell /sbin/nologin spring-boot-app;

FROM golang:1.12 as golang-build
WORKDIR /go/src/app
COPY docker docker
RUN go install -v ./...

FROM gcr.io/distroless/java:11
COPY --from=golang-build /go/bin/healthcheck /app/healthcheck
HEALTHCHECK --start-period=10s CMD ["/app/healthcheck"]

WORKDIR application
COPY --from=java-builder /etc/passwd /etc/shadow /etc/
COPY --from=java-builder application/dependencies/ ./
COPY --from=java-builder application/snapshot-dependencies/ ./
COPY --from=java-builder application/spring-boot-loader/ ./
COPY --from=java-builder application/application/ ./

USER spring-boot-app
ENV _JAVA_OPTIONS "-XX:MaxRAMPercentage=90 -Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true -Dfile.encoding=UTF-8"
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]