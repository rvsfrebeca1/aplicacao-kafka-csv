CREATE DATABASE rds_rebeca;

CREATE TABLE produtos(
  id SERIAL PRIMARY KEY,
  nome VARCHAR(50) not null,
  estoque int not null
);