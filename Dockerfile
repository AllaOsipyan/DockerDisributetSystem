FROM maven:latest as build

COPY pom.xml demo/pom.xml
COPY src demo/src
RUN mvn -f demo/pom.xml clean package -DskipTests

FROM java:latest

WORKDIR /src/java
EXPOSE 8080
COPY --from=build demo/target/demo-0.0.1-SNAPSHOT.jar /opt/demo/demo-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/opt/demo/demo-0.0.1-SNAPSHOT.jar"]


