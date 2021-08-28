--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-users
CREATE TABLE `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `enabled` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`));
--rollback drop table users;