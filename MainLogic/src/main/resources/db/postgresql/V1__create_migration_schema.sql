CREATE SCHEMA IF NOT EXISTS linksSchema;

--V1__create_migration_schema.sql
--RUN mvn -f demo/pom.xml clean package
--COPY --from=build demo/target/demo-0.0.1-SNAPSHOT.jar /opt/demo/demo-0.0.1-SNAPSHOT.jar



--spring.datasource.url=jdbc:postgresql://my-postgres:5432/links
--spring.datasource.username=postgres

--spring.flyway.url=jdbc:postgresql://my-postgres:5432/links
--spring.flyway.user=postgres
--spring.flyway.locations=classpath:db