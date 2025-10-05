# Spring Boot API
## Description
This is a simple Spring Boot API that connect to PostgreSQL database.

## Technologies
- Gradle
- Java
- Spring Boot
- PostgreSQL

## Running
Start database:
```bash
docker compose up postgres -d
```

Run the migrations:
```bash
docker compose run --rm flyway -url="jdbc:postgresql://postgres:5432/spring_boot_api?user=pg&password=pgpw" -locations="filesystem:/flyway/sql/" migrate
```

Start the project running:
```bash
./gradlew bootRun
```

## Tests
Unit:
```bash
./gradlew test

# only one
./gradlew test --tests com.alvarengacarlos.springbootapi.domain.CustomerServiceTest
```

> **Note:** Start the database before run integration tests.

Integration:
```bash
./gradlew integrationTest

# only one
./gradlew integrationTest --tests com.alvarengacarlos.springbootapi.infra.CustomerRepositoryImplTest
```

> **Note:** Start the API before run component tests.

Component:
```bash
./gradlew componentTest
```
