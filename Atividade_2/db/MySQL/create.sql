DROP DATABASE IF EXISTS Leilao_de_carros;

CREATE DATABASE IF NOT EXISTS  Leilao_de_carros;

CREATE SCHEMA IF NOT EXISTS Leilao_de_carros;

USE Leilao_de_carros;

-- CREATE TABLE IF NOT EXISTS USUARIO(
--     `user_id` SERIAL,
--     user_email VARCHAR(256) PRIMARY KEY,
--     user_senha VARCHAR(256) NOT NULL,
--     user_nome VARCHAR(256) NOT NULL,
--     user_tipo VARCHAR(20) NOT NULL DEFAULT 'PESSOA'
-- );

-- CREATE TABLE IF NOT EXISTS `PESSOA FISICA`(
--     pessoa_id SERIAL,
--     pessoa_CPF VARCHAR(11) PRIMARY KEY,
--     pessoa_email VARCHAR(256) NOT NULL,
--     pessoa_telefone VARCHAR(11) NOT NULL,
--     pessoa_sexo CHAR NOT NULL,
--     pessoa_data_nasc DATE NOT NULL,
--     pessoa_tipo VARCHAR(10) NOT NULL,
--     CONSTRAINT `check_sexo` CHECK (pessoa_sexo = 'M' OR pessoa_sexo = 'F'),
--     FOREIGN KEY (pessoa_email) REFERENCES USUARIO(user_email)
-- );

-- CREATE TABLE IF NOT EXISTS LOJA(
--     loja_id SERIAL,
--     loja_CNPJ VARCHAR(14) PRIMARY KEY,
--     loja_email VARCHAR(256) NOT NULL,
--     loja_descricao VARCHAR(256) NOT NULL,
--     FOREIGN KEY (loja_email) REFERENCES USUARIO(user_email)
-- );


-- CREATE TABLE IF NOT EXISTS VEICULO(
--     veic_id SERIAL,
--     veic_placa VARCHAR(7) PRIMARY KEY,
--     veic_loja_CNPJ VARCHAR(14),
--     veic_modelo VARCHAR(256) NOT NULL,
--     veic_chassi VARCHAR(256) NOT NULL,
--     veic_ano INT NOT NULL,
--     veic_quilometragem INT NOT NULL,
--     veic_descricao VARCHAR(256) NOT NULL,
--     veic_valor INT NOT NULL,
--     veic_fotos VARCHAR(256) NOT NULL,
--     FOREIGN KEY (veic_loja_CNPJ) REFERENCES LOJA(loja_CNPJ)
-- );


-- CREATE TABLE IF NOT EXISTS PROPOSTA(
--     prop_id SERIAL,
--     prop_pessoa_CPF VARCHAR(11),
--     prop_veic_placa VARCHAR(7),
--     prop_data DATE NOT NULL,
--     prop_valor FLOAT NOT NULL,
--     prop_status VARCHAR(256) NOT NULL,
--     prop_loja_CNPJ VARCHAR(14),
--     CONSTRAINT PK_COMPOSTA PRIMARY KEY (prop_id, prop_pessoa_CPF,
--     prop_veic_placa, prop_loja_CNPJ, prop_data),
--     FOREIGN KEY (prop_pessoa_CPF) REFERENCES `PESSOA FISICA`(pessoa_CPF),
--     FOREIGN KEY (prop_veic_placa) REFERENCES VEICULO(veic_placa),
--     FOREIGN KEY (prop_loja_CNPJ) REFERENCES LOJA(loja_CNPJ)
-- );

-- INSERT INTO USUARIO(user_email, user_senha, user_nome, user_tipo) VALUES 
-- ('admin@admin.com','admin', 'Admin', 'PESSOA');

-- INSERT INTO `PESSOA FISICA`(pessoa_CPF, pessoa_email, pessoa_sexo,
--     pessoa_telefone, pessoa_tipo, pessoa_data_nasc) VALUES ('01234567890',
--     'admin@admin.com', 'F','16970707070','ADMIN', '2001-12-05');