INSERT INTO teams(team_title,team_description) VALUES ('FSP','Financial services platform');
INSERT INTO teams(team_title,team_description) VALUES ('Ras-reporting','Russian Reporting Group');


INSERT INTO users (first_name,last_name,patronymic,birthday,position,team) VALUES ('Alex','Katz', 'Katz', '2003-12-28', 'junior java developer', 1);
INSERT INTO users (first_name,last_name,patronymic,birthday,position,team) VALUES ('Bilbo','Baggins', 'Baggins', '1985-01-22', 'teamlead', 1);
INSERT INTO users (first_name,last_name,patronymic,birthday,position,team) VALUES ('Din','Djarin', 'Djarin', '1993-05-01', 'junior analyst', 1);
INSERT INTO users (first_name,last_name,patronymic,birthday,position,team) VALUES ('Elizabeth','Windsor', 'Windsor', '1978-06-06', 'chief accountant', 2);
INSERT INTO users (first_name,last_name,patronymic,birthday,position,team) VALUES ('Ahsoka','Tano', 'Tano', '1992-11-18', 'BI junior analyst', 2);
INSERT INTO users (first_name,last_name,patronymic,birthday,position,team) VALUES ('Boba','Fett', 'Fett', '1987-02-28', 'sinior java developer', 1);

INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('Provision','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Расчет оценочных резервов для офшорных зон.', 'assigned', 5, 4, 6);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('Deals','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Добавление поля для сделочной информации', 'resolved', 3, 4, 1);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('Accounting','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Добавление поля для архивных счетов', 'in_progress', 3, 4, 1);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('Reports','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Добавление отчета по ссылочной целостности', 'resolved', 6, 4, 3);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('Hotfix','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Доработка формы 0409701' , 'reopened', 3, 2, 6);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('Hotfix','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Доработка формы 0409305' , 'reopened', 3, 2, 6);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('Hotfix','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Доработка формы 0409101' , 'assigned', 3, 4, 2);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('Hotfix','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Доработка формы 0409303' , 'in_progress', 3, 4, 2);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('Exchange_deals','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Бизнес требования для биржевых сделок' , 'in_progress', 3, 2, 4);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('FI_deals','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Анализ сделочного модуля FI' , 'assigned', 4, 2, 5);
INSERT INTO tasks (title,create_date_time,update_date_time,deadline,description,status,requested_by,assigned_by,assignee_id) VALUES ('FX_deals','2024-01-31 06:00:00','2024-01-31 06:00:00','2025-01-01 09:00:00','Анализ сделочного модуля FX' , 'in_progress', 4, 2, 5);


insert into phones (user_id, phone_number)
values (1, '+7-812-999-99-99'),
       (1, '+7-911-555-55-55'),
       (3, '+7-911-111-11-11'),
       (4, '+7-911-222-22-22'),
       (5, '+7-911-333-33-33'),
       (6, '+7-911-444-44-44'),
       (6, '+7-812-666-66-66'),
       (1, '+7-812-888-88-88'),
       (2, '+7-812-777-77-77');

insert into emails (user_id, email)
values (1, 'Alex@gmail.com'),
       (2, 'Bilbo@mail.ru'),
       (3, 'Din@gmail.ru'),
       (4, 'Elizabeth@mail.ru'),
       (5, 'Ahsoka@yandex.ru'),
       (6, 'Boba@gmail.ru'),
       (1, 'Alex@yandex.ru');