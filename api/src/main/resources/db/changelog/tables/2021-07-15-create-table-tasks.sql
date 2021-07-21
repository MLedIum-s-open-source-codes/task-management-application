--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-tasks
CREATE TABLE `tasks` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `desk_id` BIGINT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(255) NULL,
    `is_completed` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`, `desk_id`),
    FOREIGN KEY (desk_id) REFERENCES desks (id));
--rollback drop table tasks;