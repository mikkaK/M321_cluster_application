FROM gradle:jdk21-alpine AS build
LABEL authors="mikka.kummer"
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle --no-daemon bootJar

FROM openjdk:21
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
EXPOSE 8083
CMD ["java","-jar", "-Xmx2g", "/app/spring-boot-application.jar"]