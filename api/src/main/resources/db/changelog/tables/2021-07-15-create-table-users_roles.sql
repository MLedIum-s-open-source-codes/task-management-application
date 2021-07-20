--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-users_roles
CREATE TABLE `task_management`.`users_roles` (
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`id`),
    FOREIGN KEY (`role_id`) REFERENCES roles (`id`));
--rollback drop table users_roles;