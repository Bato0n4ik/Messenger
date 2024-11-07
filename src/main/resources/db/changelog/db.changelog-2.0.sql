--liquibase formatted sql

--changeset andreev:1
ALTER TABLE users ADD COLUMN image VARCHAR(128);