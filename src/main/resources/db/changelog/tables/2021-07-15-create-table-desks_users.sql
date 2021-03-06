--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-desks_users
CREATE TABLE `desks_users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `desk_id` BIGINT NOT NULL,
    `hidden` TINYINT NOT NULL DEFAULT 0,
    `is_owner` TINYINT NOT NULL DEFAULT 0,
    `is_active` TINYINT NOT NULL DEFAULT 1,
    `created_by` BIGINT DEFAULT NULL,
    `created_date` TIMESTAMP DEFAULT NULL,
    `last_modified_by` BIGINT DEFAULT NULL,
    `last_modified_date` TIMESTAMP DEFAULT NULL,
    PRIMARY KEY (`id`, `user_id`, `desk_id`),
    FOREIGN KEY (`user_id`) REFERENCES users (`id`),
    FOREIGN KEY (`desk_id`) REFERENCES desks (`id`));
--rollback drop table desks_users;
