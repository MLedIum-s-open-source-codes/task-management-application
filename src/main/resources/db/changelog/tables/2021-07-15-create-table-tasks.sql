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
    PRIMARY KEY (`id`, `desk_id`),
    FOREIGN KEY (desk_id) REFERENCES desks (id));
--rollback drop table tasks;