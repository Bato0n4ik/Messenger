--liquibase formatted sql

--changeset andrew:1
ALTER TABLE users ADD COLUMN image VARCHAR(128);