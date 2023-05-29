DROP TABLE IF EXISTS role_name;
CREATE TABLE role_name (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  seq INTEGER
);

DROP TABLE IF EXISTS role;
CREATE TABLE role (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  role TEXT NOT NULL,
  username TEXT NOT NULL
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	username TEXT NOT NULL,
	password TEXT NOT NULL,
	created TEXT NOT NULL,
	updated TEXT NOT NULL,
	email TEXT NOT NULL
);

DROP TABLE IF EXISTS user_history;
CREATE TABLE user_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
	name TEXT NOT NULL,
	username TEXT NULL,
	password TEXT NULL,
	role TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL,
	email TEXT NULL
);


DROP TRIGGER IF EXISTS user_trigger;
CREATE TRIGGER user_trigger 
   AFTER UPDATE ON users
BEGIN
	INSERT INTO user_history (
			user_id,
			name,
			username,
			password,
			role,
			email,
			created,
			updated
		)
	VALUES
		(
			new.id,
			new.name,
			new.username,
			new.password,
			new.role,
			new.email,
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;

DROP TABLE IF EXISTS container_status;
CREATE TABLE container_status (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  seq INTEGER
);


DROP TABLE IF EXISTS campaign;
CREATE TABLE campaign (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	status TEXT NOT NULL,
	cleaner TEXT NULL,
	cleaned TEXT NULL,
	expires TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TABLE IF EXISTS campaign_history;
CREATE TABLE campaign_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    campaign_id INTEGER,
	name TEXT NOT NULL,
	status TEXT NOT NULL,
	cleaner TEXT NULL,
	cleaned TEXT NULL,
	expires TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TRIGGER IF EXISTS campaign_trigger;
CREATE TRIGGER campaign_trigger 
   AFTER UPDATE ON campaign
BEGIN
	INSERT INTO campaign_history (
			campaign_id,
			name,
			status,
			cleaner,
			cleaned,
			expires,
			created,
			updated
		)
	VALUES
		(
			new.id,
			new.name,
			new.status,
			new.cleaner,
			new.cleaned,
			new.expires,
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;

DROP TABLE IF EXISTS container;
CREATE TABLE container (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	status TEXT NOT NULL,
	cleaner TEXT NULL,
	cleaned TEXT NULL,
	expires TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TABLE IF EXISTS container_history;
CREATE TABLE container_history (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    container_id INTEGER,
	name TEXT NOT NULL,
	status TEXT NOT NULL,
	cleaner TEXT NULL,
	cleaned TEXT NULL,
	expires TEXT NULL,
	created TEXT NULL,
	updated TEXT NULL
);

DROP TRIGGER IF EXISTS container_trigger;
CREATE TRIGGER container_trigger 
   AFTER UPDATE ON container
BEGIN
	INSERT INTO container_history (
			container_id,
			name,
			status,
			cleaner,
			cleaned,
			expires,
			created,
			updated
		)
	VALUES
		(
			new.id,
			new.name,
			new.status,
			new.cleaner,
			new.cleaned,
			new.expires,
			new.created,
			strftime('%Y-%m-%dT%H:%M:%f','now')
		);
END;

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	created_date TEXT NOT NULL,
	updated_date TEXT NULL,
	serial_number TEXT NOT NULL,
	product_status TEXT NOT NULL
);

DROP TABLE IF EXISTS price;
CREATE TABLE price (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name    TEXT  NOT NULL,
  price_type    TEXT  NULL
);

DROP TABLE IF EXISTS price_type;
CREATE TABLE price_type (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  type    TEXT NOT NULL,
  seq     INTEGER
);

DROP TABLE IF EXISTS city;
CREATE TABLE city (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    state TEXT NOT NULL,
    country TEXT NOT NULL
);

DROP TABLE IF EXISTS location;
CREATE TABLE location (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    city TEXT NOT NULL
);

DROP TABLE IF EXISTS user_image;
CREATE TABLE user_image (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	user_id INTEGER NOT NULL, 
	photo CLOB NOT NULL
);

select `id`, `user_id`, `photo` from user_image where `user_id` = 2

