FROM maven:3-openjdk-11 as build

COPY pom.xml ./

COPY src ./src
RUN mvn package

FROM payara/server-full
COPY --from=build /target/restAPI-1.0.war $DEPLOY_DIR