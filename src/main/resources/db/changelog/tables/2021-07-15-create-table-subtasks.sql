--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-subtasks
CREATE TABLE `subtasks` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `task_id` BIGINT NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `completed` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`, `task_id`),
    FOREIGN KEY (task_id) REFERENCES tasks (id));
--rollback drop table subtasks;