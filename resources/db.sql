CREATE SCHEMA kdmeubichinho;
USE kdmeubichinho;

CREATE TABLE especie(
	id_especie INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL UNIQUE
) ENGINE = InnoDB;

CREATE TABLE foto (
    id_foto INT PRIMARY KEY AUTO_INCREMENT,
    caminho VARCHAR(255)
)ENGINE = InnoDB;

CREATE TABLE pessoa (
    id_pessoa INT PRIMARY KEY AUTO_INCREMENT,
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
)ENGINE = InnoDB;

CREATE TABLE animal (
    id_animal INT PRIMARY KEY AUTO_INCREMENT,
    sexo ENUM("Macho", "Fêmea", "Não identificado") NOT NULL,
    classificacao_etaria ENUM("Filhote", "Adulto") NOT NULL,
    porte ENUM("Pequeno","Médio", "Grande") NOT NULL,
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
    FOREIGN KEY (`fk_id_especie`) REFERENCES `especie`(`id_especie`),
    FOREIGN KEY (`fk_id_foto`) REFERENCES `foto`(`id_foto`)
)ENGINE = InnoDB;

CREATE TABLE categoria(
	id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    classificacao VARCHAR(100) NOT NULL
)ENGINE = InnoDB;

CREATE TABLE anuncio (
    id_anuncio INT PRIMARY KEY AUTO_INCREMENT,
    status_anuncio ENUM("Ativo", "Inativo"),
    data_criacao DATETIME NOT NULL,
    data_encerramento DATETIME,
    fk_id_pessoa INT NOT NULL,
    fk_id_animal INT NOT NULL,
    fk_id_categoria INT NOT NULL,
    FOREIGN KEY (`fk_id_pessoa`) REFERENCES `pessoa`(`id_pessoa`),
	FOREIGN KEY (`fk_id_animal`) REFERENCES `animal`(`id_animal`),
	FOREIGN KEY (`fk_id_categoria`) REFERENCES `categoria`(`id_categoria`)
)ENGINE = InnoDB;

CREATE TABLE mensagem (
    id_mensagem INT PRIMARY KEY AUTO_INCREMENT,
    data_mensagem DATETIME NOT NULL,
    mensagem VARCHAR(255),
    fk_id_pessoa INT,
    fk_id_anuncio INT,
	FOREIGN KEY (`fk_id_pessoa`) REFERENCES `pessoa`(`id_pessoa`),
	FOREIGN KEY (`fk_id_anuncio`) REFERENCES `anuncio`(`id_anuncio`)
)ENGINE = InnoDB;

-- ALTER TABLE `foto` ADD CONSTRAINT `fk_id_animal` FOREIGN KEY (`fk_id_animal`) REFERENCES `animal`(`id_animal`);
-- ALTER TABLE `animal` ADD CONSTRAINT `fk_id_especie` FOREIGN KEY (`fk_id_especie`) REFERENCES `especie`(`id_especie`);
-- ALTER TABLE `anuncio` ADD CONSTRAINT `fk_id_pessoa` FOREIGN KEY (`fk_id_pessoa`) REFERENCES `pessoa`(`id_pessoa`);
-- ALTER TABLE `anuncio` ADD CONSTRAINT `fk_id_animal` FOREIGN KEY (`fk_id_animal`) REFERENCES `animal`(`id_animal`);
-- ALTER TABLE `anuncio` ADD CONSTRAINT `fk_id_categoria` FOREIGN KEY (`fk_id_categoria`) REFERENCES `categoria`(`id_categoria`);
-- ALTER TABLE `mensagem` ADD CONSTRAINT `fk_id_pessoa` FOREIGN KEY (`fk_id_pessoa`) REFERENCES `pessoa`(`id_pessoa`);
-- ALTER TABLE `mensagem` ADD CONSTRAINT `fk_id_anuncio` FOREIGN KEY (`fk_id_anuncio`) REFERENCES `anuncio`(`id_anuncio`);
    
INSERT INTO `kdmeubichinho`.`especie` (`nome`) VALUES ('Cachorro'), ('Gato'), ('Pássaro'), ('Roedor');
INSERT INTO `kdmeubichinho`.`categoria` (`classificacao`) VALUES ('Fugiu de casa'), ('Encontrado na rua'), ('Procurando um lar');