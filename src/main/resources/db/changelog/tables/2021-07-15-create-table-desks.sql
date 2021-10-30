--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-desks
CREATE TABLE `desks` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `is_active` TINYINT NOT NULL DEFAULT 1,
    `created_by` BIGINT DEFAULT NULL,
    `created_date` TIMESTAMP DEFAULT NULL,
    `last_modified_by` BIGINT DEFAULT NULL,
    `last_modified_date` TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (`id`));
--rollback drop table desks;
