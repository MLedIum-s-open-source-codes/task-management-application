--liquibase formatted sql

--changeset mledium:2021-07-15-insert-into-table-roles
INSERT INTO `roles` (`name`, `description`) VALUES ('ADMIN', 'Администратор');
INSERT INTO `roles` (`name`, `description`) VALUES ('USER', 'Пользователь');