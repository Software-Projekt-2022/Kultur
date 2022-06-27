ARG HOST
ARG PORT
ARG DB
ARG USER
ARG PW

FROM gradle:7.4.2-jdk11 as build

COPY ./ ./
RUN gradle bootJar
RUN echo $(pwd)

FROM openjdk:17-alpine
EXPOSE 8080
EXPOSE 80
RUN mkdir /Kultur
COPY --from=build /home/gradle/build/libs/*.jar /Kultur/app.jar
RUN chmod +rwx /Kultur -R
ENTRYPOINT ["java","-jar","/Kultur/app.jar", "--CC_HOST=${HOST}","--CC_PORT=${PORT}","--CC_DB=${DB}","--CC_USER=${USER}","--CC_PW=${PW}"]