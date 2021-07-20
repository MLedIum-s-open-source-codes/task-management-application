--liquibase formatted sql

--changeset mledium:2021-07-15-insert-into-table-users_roles
INSERT INTO `questionnaire`.`users_roles` (`user_id`, `role_id`) VALUES ((SELECT id FROM users where username = 'Admin'), (SELECT id FROM roles where name = 'ADMIN'));
INSERT INTO `questionnaire`.`users_roles` (`user_id`, `role_id`) VALUES ((SELECT id FROM users where username = 'Admin'), (SELECT id FROM roles where name = 'USER'));
INSERT INTO `questionnaire`.`users_roles` (`user_id`, `role_id`) VALUES ((SELECT id FROM users where username = 'User'), (SELECT id FROM roles where name = 'USER'));
