CREATE TABLE IF NOT EXISTS tasks (
    task_id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(50) NOT NULL,
    create_date_time timestamp NOT NULL,
    update_date_time timestamp NOT NULL,
    description text NOT NULL,
    status  ENUM('assigned', 'in_progress', 'resolved', 'reopened', 'closed') NOT NULL,
    requestedBy int NULL,
    assignedBy int NULL,
    assigneeID int NULL,
    FOREIGN KEY(requestedBy) REFERENCES users(users_id) ON DELETE SET NULL,
    FOREIGN KEY(assignedBy) REFERENCES users(users_id ON DELETE SET NULL,
    FOREIGN KEY(assigneeID) REFERENCES users(users_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS users (
    user_id int AUTO_INCREMENT PRIMARY KEY,
    firstName varchar(20) NOT NULL,
    lastName varchar(50) NOT NULL,
    patronymic varchar(50) NOT NULL,
    birthday date NOT NULL,
    position varchar(50) NOT NULL,
    team int NULL,
    email varchar(50) NULL,
    phone varchar(50) NULL,
    FOREIGN KEY(team) REFERENCES teams(team_id) ON DELETE SET NULL,
    FOREIGN KEY(email) REFERENCES emails(email_id) ON DELETE SET NULL,
    FOREIGN KEY(phone) REFERENCES phones(phone_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS phones (
    phone_id int AUTO_INCREMENT PRIMARY KEY,
    user_id  int NULL,
    phone_number varchar NOT NULL UNIQUE,
    UNIQUE (user_id, phone_number),
    FOREIGN KEY(user_id) REFERENCES users(users_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS emails (
    email_id int AUTO_INCREMENT PRIMARY KEY,
    user_id  int NULL,
    email varchar NOT NULL UNIQUE,
    UNIQUE (user_id, email),
    FOREIGN KEY(user_id) REFERENCES users(users_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS teams (
    team_id int AUTO_INCREMENT PRIMARY KEY,
    team_title varchar(50) NOT NULL,
    team_description varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS teamMembers (
    team_id int NOT NULL,
    user_id int NULL,
    FOREIGN KEY(team_id) REFERENCES teams(team_id) ON DELETE CASCADE,
    FOREIGN KEY(user_id) REFERENCES users(users_id) ON DELETE SET NULL
);