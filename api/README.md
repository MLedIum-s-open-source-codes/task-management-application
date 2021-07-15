## Приложение для управления задачами

Задача: спроектировать и разработать базу API для приложения управления задачами.

### Rest API

Краткая документация по запросам к api создана с помощью Swagger. Посмотреть ее можно запустив проект на локальной машине и перейдя по ссылке: http://localhost:8080/swagger-ui.html

### Как запустить приложение

Заранее настройте базу данных MySQL:
\
Создайте схему: questionnaire
\
и пользователя:
\
username: test_user
\
password: password
\
или укажите в application.properties свои данные

Далее запустите проект в IntelliJ IDEA и нажмите run

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