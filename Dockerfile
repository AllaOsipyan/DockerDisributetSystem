FROM maven:latest as build
COPY pom.xml demo/pom.xml
COPY MainLogic/pom.xml demo/MainLogic/pom.xml
COPY MainLogic/src demo/MainLogic/src
RUN mvn -f demo/MainLogic/pom.xml clean package -DskipTests

FROM java:latest

WORKDIR /src/java
EXPOSE 8080
COPY --from=build demo/MainLogic/target/MainLogic-0.0.1-SNAPSHOT.jar /opt/demo/MainLogic/MainLogic-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/opt/demo/MainLogic/MainLogic-0.0.1-SNAPSHOT.jar"]


