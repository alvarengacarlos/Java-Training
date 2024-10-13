# JDBC

## Description
This is a very simple JDBC example.

## Technologies:
- Java
- Mysql DB
- JDBC

## Running
> **Requirements:** 
> - Maven
> - Java (JDK)
> - Docker
> - Docker Compose  

- Start Mysql DB:
```bash
docker compose up
```

- Access the container:
```bash
docker container exec -it mysql-db bash
```

- Inside the container execute the `main.sql` file. The user password is `userpw`:
```bash
mysql -u user -p < /tmp/main.sql
```

- Outside the container compile Java project:
```bash
mvn package
```

- Outside the container execute the Java app:
```bash
java -jar app
```

## References
- [Java SE API](https://docs.oracle.com/en/java/javase/22/docs/api/java.sql/module-summary.html)
- [Mysql Documentation](https://dev.mysql.com/doc/refman/8.4/en/)
- [Java Tutorials](https://docs.oracle.com/javase/tutorial/jdbc/index.html)