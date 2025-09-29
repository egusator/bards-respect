CREATE TABLE users (
    id           BIGSERIAL PRIMARY KEY,
    login        VARCHAR(20) UNIQUE NOT NULL,
    password     VARCHAR(100)       NOT NULL,
    name         VARCHAR(50),
    email        VARCHAR(50) UNIQUE
);

CREATE TABLE role (
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE user_role (
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT,
    role_id      BIGINT
);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_to_users
        FOREIGN KEY (user_id)
            REFERENCES users (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_to_role
        FOREIGN KEY (role_id)
            REFERENCES role (id);

INSERT INTO role (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_EDITOR'),
    ('ROLE_USER');