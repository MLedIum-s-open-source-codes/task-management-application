--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-desks
CREATE TABLE `desks` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(255) NULL,
    PRIMARY KEY (`id`));
--rollback drop table desks;