--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-tasks_users
CREATE TABLE `tasks_users` (
    `task_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`task_id`, `user_id`),
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (user_id) REFERENCES users (id));
--rollback drop table desks_users;