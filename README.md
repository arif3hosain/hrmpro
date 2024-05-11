# Its ongoing project

Development of a human resources management platform:
- Creation of an employee management system, including personal information, schedules, leave, etc.
- Implementation of an employee performance monitoring system
- Development of a benefits and compensation management system
- Integration of a recruit ment and applicant management system



Technologies to use:
Spring boot (spring data jpa, spring validation, spring security, spring MV

Technologies to use:
Spring boot (spring data jpa, spring validation, spring security, spring MVC with thymeleaf as a template engine)
DBMS: MySQL

## Development Tool
- Eclipse
- IntellIJ IDEA

#Hrmpro


## Tools

- MySQL
- Jakarta EE
- Spring Bot
- Spring MVC
- Thymeleaf
- Hibernate
- Spring JPA
- Apache Maven
- J2EE
- Java 21

## Development

Update your local database connection in `application.properties` or create your own `application-local.properties` file to override
settings for development.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options".

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/hrmpro-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=com.hrm/hrmpro
```

