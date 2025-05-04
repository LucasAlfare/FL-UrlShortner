FROM gradle:8.10.2-alpine as gradle_stage
LABEL authors="Francisco Lucas"
COPY . /app
WORKDIR /app
RUN cd /app
RUN gradle clean
# gradle assemble is used to build without running tests
# also daemons are not needed because gradle will be discarded
RUN gradle assemble --no-daemon

FROM eclipse-temurin:21-alpine as jdk_stage
EXPOSE 80
RUN mkdir /app
COPY --from=gradle_stage /app /app
ENTRYPOINT ["java", "-jar", "/app/build/libs/FL-UrlShortner-1.0.jar"]