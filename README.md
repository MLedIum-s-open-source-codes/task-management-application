### Как запустить:

Выполните следующие команды, предварительно установив maven и docker:
\
mvn clean package
\
docker build  -t tma:v1 .
\
docker run --name TMA -p 8080:8080 tma:v1


<!--
После умпешного выполнения команд, перейдите по ссылке: http://localhost:8080/
-->

### Стек Backend API

Spring Boot
\
Spring Data Rest,
\
Spring Data JPA, MySQL

Lombok, Log4j2,
\
Swagger,
LiquiBase
