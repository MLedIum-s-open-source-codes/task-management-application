--liquibase formatted sql

--changeset mledium:2021-07-15-insert-into-table-users
INSERT INTO `users` (`username`, `password`) VALUES ('Admin', '$2a$10$lJO1SCml/cGqZbDalnx3ku0UqsE2vtAgnRpv5bex56EHdiw1v7vwe');
INSERT INTO `users` (`username`, `password`) VALUES ('User', '$2a$10$lJO1SCml/cGqZbDalnx3ku0UqsE2vtAgnRpv5bex56EHdiw1v7vwe');
