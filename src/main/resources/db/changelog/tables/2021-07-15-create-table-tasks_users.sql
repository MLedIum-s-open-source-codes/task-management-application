--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-tasks_users
CREATE TABLE `tasks_users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `desk_user_id` BIGINT NOT NULL,
    `task_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `is_active` TINYINT NOT NULL DEFAULT 1,
    `created_by` BIGINT DEFAULT NULL,
    `created_date` TIMESTAMP DEFAULT NULL,
    `last_modified_by` BIGINT DEFAULT NULL,
    `last_modified_date` TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (`id`, `desk_user_id`, `task_id`, `user_id`),
    FOREIGN KEY (`desk_user_id`) REFERENCES desks_users (`id`),
    FOREIGN KEY (`task_id`) REFERENCES tasks (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`id`));
--rollback drop table tasks_users;
