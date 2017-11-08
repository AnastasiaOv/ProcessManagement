DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;



-- Table: users
CREATE SEQUENCE global_seq START 100000;
CREATE TABLE users (
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);


-- Table: roles
CREATE TABLE roles (
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(100) NOT NULL
);


-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
);


-- Insert data

INSERT INTO users VALUES (1, 'proselyte', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2);