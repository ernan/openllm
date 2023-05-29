insert into role_name(seq, name) VALUES (1, "Coordinator");
insert into role_name(seq, name) VALUES (2, "Supervisor");
insert into role_name(seq, name) VALUES (3, "Administrator");

insert into users(name, username, email, password, created, updated) values ('Administrator','admin','administrator@phizor.com','admin','2023-05-12','2023-05-12');
insert into users(name, username, email, password, created, updated) values ('Coordinator','coordinator','coordinator@phizor.com','coordinator','2023-05-12','2023-05-12');
insert into users(name, username, email, password, created, updated) values ('Supervisor','supervisor','supervisor@phizor.com','supervisor','2023-05-12','2023-05-12');
insert into users(name, username, email, password, created, updated) values ('Amy Henderson','amy','amy.henderson@phizor.com','amy','2023-05-12','2023-05-12');
insert into users(name, username, email, password, created, updated) values ('Stephanie Moore','stephanie','stephanie.moore@phizor.com','stephanie','2023-05-12','2023-05-12');

insert into role(username, role) values ('Administrator','Administrator');
insert into role(username, role) values ('Coordinator','Coordinator');
insert into role(username, role) values ('Supervisor','Supervisor');
insert into role(username, role) values ('amy','Supervisor');
insert into role(username, role) values ('stephanie','Coordinator');

insert into campaign(name, status, created, updated) VALUES ("Campaign 1", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 2", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 3", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 4", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 5", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 6", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 7", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 8", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 9", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 10", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 11", "INITIAL", DATE(), DATE());
insert into campaign(name, status, created, updated) VALUES ("Campaign 12", "INITIAL", DATE(), DATE());

insert into container_status(seq, name) VALUES (1, "CLEAN");
insert into container_status(seq, name) VALUES (2, "CLOSE_TO_EXPIRY");
insert into container_status(seq, name) VALUES (3, "EXPIRED");
insert into container_status(seq, name) VALUES (4, "INITIAL");

insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 1", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());
insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 2", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());
insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 3", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());
insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 4", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());
insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 5", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());
insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 6", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());
insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 7", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());
insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 8", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());
insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 9", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());
insert into container(name, status, cleaner, cleaned, expires, created, updated) VALUES ("Container 10", "INITIAL", "admin", DATE(), DATE(), DATE(), DATE());

INSERT INTO price_type(seq, type) VALUES (1, 'M');
INSERT INTO price_type(seq, type) VALUES (2, 'R');
INSERT INTO price_type(seq, type) VALUES (3, 'H');

insert into city (name, state, country) values ('San Francisco', 'CA', 'US');
insert into city (name, state, country) values ('Kildare', 'KD', 'IE');
insert into city (name, state, country) values ('Munich', 'MN', 'GE');

insert into location (name, city) values ('Loc 01', 'Kildare');
insert into location (name, city) values ('Loc 02', 'Kildare');

