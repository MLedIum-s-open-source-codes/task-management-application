--liquibase formatted sql

--changeset mledium:2021-07-15-insert-into-table-users
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('Admin', '$2a$10$lJO1SCml/cGqZbDalnx3ku0UqsE2vtAgnRpv5bex56EHdiw1v7vwe', '1');
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('User', '$2a$10$lJO1SCml/cGqZbDalnx3ku0UqsE2vtAgnRpv5bex56EHdiw1v7vwe', '1');
