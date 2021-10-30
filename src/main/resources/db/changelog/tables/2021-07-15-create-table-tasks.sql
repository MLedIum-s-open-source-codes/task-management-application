--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-tasks
CREATE TABLE `tasks` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `desk_id` BIGINT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `note` VARCHAR(255) NULL,
    `completed` TINYINT NOT NULL DEFAULT 0,
    `important` TINYINT NOT NULL DEFAULT 0,
    `complete_before_date` TIMESTAMP NULL,
    `is_active` TINYINT NOT NULL DEFAULT 1,
    `created_by` BIGINT DEFAULT NULL,
    `created_date` TIMESTAMP DEFAULT NULL,
    `last_modified_by` BIGINT DEFAULT NULL,
    `last_modified_date` TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (`id`, `desk_id`),
    FOREIGN KEY (`desk_id`) REFERENCES desks (`id`));
--rollback drop table tasks;
