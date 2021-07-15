--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-users
CREATE TABLE `task_management`.`users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    --`password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`));
--rollback drop table users;