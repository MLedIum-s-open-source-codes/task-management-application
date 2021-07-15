--liquibase formatted sql

--changeset mledium:2021-07-15-create-table-users_desks
CREATE TABLE `task_management`.`users_desks` (
    `user_id` BIGINT NOT NULL,
    `desk_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `desk_id`),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (desk_id) REFERENCES desks (id));
--rollback drop table users_desks;