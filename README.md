### How to launch:

Run the following commands after installing docker:
\
./mvnw clean package
\
docker build  -t tma:v1 .
\
docker run --name TMA -p 8080:8080 tma:v1


<!--
After successful execution of the commands, follow the link: http://localhost:8080/
-->

### Stack Backend API

Spring Boot,
\
Spring Web,
\
Spring Security, jwt
\
Spring Data JPA, MySQL, H2

LiquiBase
\
Lombok
\
Swagger
