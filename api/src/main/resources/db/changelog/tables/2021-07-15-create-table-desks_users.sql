--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-desks_users
CREATE TABLE `desks_users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `desk_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `hidden` TINYINT NOT NULL DEFAULT 0,
    `owner` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`, `desk_id`, `user_id`),
    FOREIGN KEY (desk_id) REFERENCES desks (id),
    FOREIGN KEY (user_id) REFERENCES users (id));
--rollback drop table desks_users;