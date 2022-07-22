CREATE DATABASE  IF NOT EXISTS  Leilao_de_carros;

CREATE SCHEMA IF NOT EXISTS Leilao_de_carros;

USE Leilao_de_carros;

CREATE TABLE IF NOT EXISTS LOJA(
    loja_id serial NOT NULL,
    loja_CNPJ VARCHAR(14) PRIMARY KEY,
    loja_email VARCHAR(256) NOT NULL,
    loja_senha VARCHAR(50) NOT NULL,
    loja_nome VARCHAR(256) NOT NULL,
    loja_descricao VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS USUARIO(
    `user_id` serial ,
    user_CPF VARCHAR(11) PRIMARY KEY,
    user_login VARCHAR(256) NOT NULL,
    user_senha VARCHAR(256) NOT NULL,
    user_email VARCHAR(256) NOT NULL,
    user_nome VARCHAR(256) NOT NULL,
    user_telefone VARCHAR(11) NOT NULL,
    user_sexo CHAR NOT NULL,
    user_data_nasc DATE NOT NULL,
    user_tipo VARCHAR(10) NOT NULL,
    CONSTRAINT `check_sexo` CHECK (user_sexo == 'M' || user_sexo == 'F')
);

CREATE TABLE IF NOT EXISTS VEICULO(
    veic_id serial,
    veic_placa VARCHAR(7) PRIMARY KEY,
    veic_loja_CNPJ VARCHAR(14),
    veic_modelo VARCHAR(256) NOT NULL,
    veic_chassi VARCHAR(256) NOT NULL,
    veic_ano INT NOT NULL,
    veic_quilometragem BIGINT NOT NULL,
    veic_descricao VARCHAR(256) NOT NULL,
    veic_valor FLOAT NOT NULL,
    veic_fotos VARCHAR(256) NOT NULL,
    FOREIGN KEY (veic_loja_CNPJ) REFERENCES LOJA(loja_CNPJ)
);


CREATE TABLE IF NOT EXISTS PROPOSTA(
    prop_id serial NOT NULL,
    prop_user_CPF VARCHAR(11),
    prop_veic_placa VARCHAR(7),
    prop_data DATE NOT NULL,
    prop_valor VARCHAR(50) NOT NULL,
    prop_status VARCHAR(256) NOT NULL,
    prop_loja_CNPJ VARCHAR(14),
    CONSTRAINT PK_COMPOSTA PRIMARY KEY (prop_user_CPF, prop_veic_placa, prop_loja_CNPJ),
    FOREIGN KEY (prop_user_CPF) REFERENCES USUARIO(user_CPF),
    FOREIGN KEY (prop_veic_placa) REFERENCES VEICULO(veic_placa),
    FOREIGN KEY (prop_loja_CNPJ) REFERENCES LOJA(loja_CNPJ)
);