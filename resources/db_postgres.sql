CREATE DATABASE kdmeubichinho;
--CREATE SCHEMA kdmeubichinho;

CREATE TABLE especie(
	id_especie SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE foto (
    id_foto SERIAL PRIMARY KEY,
    caminho VARCHAR(255)
);

CREATE TABLE pessoa (
    id_pessoa SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(80) NOT NULL UNIQUE,
    cep VARCHAR(10) NOT NULL,
    logradouro VARCHAR(255),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    localidade VARCHAR(150),
    uf VARCHAR(2),
    ibge VARCHAR(50),
    ddd VARCHAR(5),    
    numero_residencial VARCHAR(10),
    celular VARCHAR(16) NOT NULL,
    senha VARCHAR(70) NOT NULL,
    admin BOOL NOT NULL DEFAULT false
);

CREATE TYPE sexo AS ENUM ('Macho', 'Fêmea', 'Não identificado');
CREATE TYPE classificacao_etaria AS ENUM ('Filhote', 'Adulto');
CREATE TYPE porte AS ENUM ('Pequeno','Médio', 'Grande');

CREATE TABLE animal (
    id_animal SERIAL PRIMARY KEY,
    sexo sexo NOT NULL,
    classificacao_etaria classificacao_etaria NOT NULL,
    porte porte NOT NULL,
    castrado BOOL NOT NULL DEFAULT false,
    vacinado BOOL NOT NULL DEFAULT false,
    nome VARCHAR(120),
    cep VARCHAR(10) NOT NULL,
	logradouro VARCHAR(255),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    localidade VARCHAR(150),
    uf VARCHAR(2),
    ibge VARCHAR(50),
    ddd VARCHAR(5),    
    numero_residencial VARCHAR(10),
    fk_id_especie INT NOT NULL,
    fk_id_foto INT NOT NULL,
    FOREIGN KEY ("fk_id_especie") REFERENCES especie("id_especie"),
    FOREIGN KEY ("fk_id_foto") REFERENCES foto("id_foto")
);

CREATE TABLE categoria(
	id_categoria SERIAL PRIMARY KEY,
    classificacao VARCHAR(100) NOT NULL
);

CREATE TYPE status_anuncio AS ENUM ('Ativo','Inativo');

CREATE TABLE anuncio (
    id_anuncio SERIAL PRIMARY KEY,
    status_anuncio status_anuncio,
    data_criacao DATE NOT NULL,
    data_encerramento DATE,
    fk_id_pessoa INT NOT NULL,
    fk_id_animal INT NOT NULL,
    fk_id_categoria INT NOT NULL,
    FOREIGN KEY ("fk_id_pessoa") REFERENCES pessoa("id_pessoa"),
	FOREIGN KEY ("fk_id_animal") REFERENCES animal("id_animal"),
	FOREIGN KEY ("fk_id_categoria") REFERENCES categoria("id_categoria")
);

CREATE TABLE mensagem (
    id_mensagem SERIAL PRIMARY KEY,
    data_mensagem DATE NOT NULL,
    mensagem VARCHAR(255),
    fk_id_pessoa INT,
    fk_id_anuncio INT,
	FOREIGN KEY ("fk_id_pessoa") REFERENCES pessoa("id_pessoa"),
	FOREIGN KEY ("fk_id_anuncio") REFERENCES anuncio("id_anuncio")
);

-- ALTER TABLE `foto` ADD CONSTRAINT `fk_id_animal` FOREIGN KEY (`fk_id_animal`) REFERENCES `animal`(`id_animal`);
-- ALTER TABLE `animal` ADD CONSTRAINT `fk_id_especie` FOREIGN KEY (`fk_id_especie`) REFERENCES `especie`(`id_especie`);
-- ALTER TABLE `anuncio` ADD CONSTRAINT `fk_id_pessoa` FOREIGN KEY (`fk_id_pessoa`) REFERENCES `pessoa`(`id_pessoa`);
-- ALTER TABLE `anuncio` ADD CONSTRAINT `fk_id_animal` FOREIGN KEY (`fk_id_animal`) REFERENCES `animal`(`id_animal`);
-- ALTER TABLE `anuncio` ADD CONSTRAINT `fk_id_categoria` FOREIGN KEY (`fk_id_categoria`) REFERENCES `categoria`(`id_categoria`);
-- ALTER TABLE `mensagem` ADD CONSTRAINT `fk_id_pessoa` FOREIGN KEY (`fk_id_pessoa`) REFERENCES `pessoa`(`id_pessoa`);
-- ALTER TABLE `mensagem` ADD CONSTRAINT `fk_id_anuncio` FOREIGN KEY (`fk_id_anuncio`) REFERENCES `anuncio`(`id_anuncio`);
    
INSERT INTO especie ("nome") VALUES ('Cachorro'), ('Gato'), ('Pássaro'), ('Roedor');
INSERT INTO categoria ("classificacao") VALUES ('Fugiu de casa'), ('Encontrado na rua'), ('Procurando um lar');