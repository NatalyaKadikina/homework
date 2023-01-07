-- liquibase formatted sql

-- changeset Natalya:1
CREATE INDEX student_name_index ON student (name);

-- changeset Natalya:2
CREATE INDEX faculty_nameOrColor_index ON faculty (name, color);