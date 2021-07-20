--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-roles
CREATE TABLE roles (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(32) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`));
--rollback drop table roles;