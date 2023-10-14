# Very Simple E-Commerce
## Description
- This is a very simple e-commerce created to my JPA learning and cannot be used in a production environment.
- The goal is use Hibernate ORM to map the objects to relational database. Also, it is very important not to depend on Hibernate ORM. In this case, this project will depend on Jakarta EE Persistence. 

## Run the project
- The application does not have interface, then all execution is made through integration tests.

- Start Maria DB container:
```bash
docker compose -f docker-compose-dev.yaml  up
```

- Run integration tests:
```bash
mvn integration-test
```

## Technologies
- MariaDB
- Java
- Maven

## References
- [Hibernate](https://hibernate.org/orm/documentation/6.2/)

- [Jetbrains blog](https://blog.jetbrains.com/idea/2021/02/creating-a-simple-jakarta-persistence-application/)

- [MariaDb](https://mariadb.com/kb/en/about-mariadb-connector-j/)