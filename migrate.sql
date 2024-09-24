CREATE TABLE registered_user (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  senha VARCHAR(255) NOT NULL
);

CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  age INTEGER NOT NULL,
  class VARCHAR(50) NOT NULL
);

CREATE TABLE audit (
  id SERIAL PRIMARY KEY,
  registered_user_id INTEGER REFERENCES registered_user(id),
  http_method VARCHAR(10) NOT NULL,
  endpoint VARCHAR(255) NOT NULL,
  request TEXT,
  response TEXT,
  date_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);



