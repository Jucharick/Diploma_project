
CREATE TABLE IF NOT EXISTS teams (
    team_id int AUTO_INCREMENT PRIMARY KEY,
    team_title varchar(50) NOT NULL,
    team_description varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    user_id int AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(20) NOT NULL,
    last_name varchar(50) NOT NULL,
    patronymic varchar(50) NOT NULL,
    birthday date NOT NULL,
    position varchar(50) NOT NULL,
    team int NULL,
    email varchar(250) NULL,
    phone varchar(250) NULL,
    FOREIGN KEY(team) REFERENCES teams(team_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    task_id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(50) NOT NULL,
    create_date_time timestamp NOT NULL,
    update_date_time timestamp NOT NULL,
    deadline timestamp NOT NULL,
    description text NOT NULL,
    status  ENUM('assigned', 'in_progress', 'resolved', 'reopened', 'closed') NOT NULL,
    requested_by int NULL,
    assigned_by int NULL,
    assignee_id int NULL,
    FOREIGN KEY(requested_by) REFERENCES users(user_id) ON DELETE SET NULL,
    FOREIGN KEY(assigned_by) REFERENCES users(user_id) ON DELETE SET NULL,
    FOREIGN KEY(assignee_id) REFERENCES users(user_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS teamMembers (
    team_id int NOT NULL,
    user_id int NULL,
    FOREIGN KEY(team_id) REFERENCES teams(team_id) ON DELETE CASCADE,
    FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE SET NULL
);
