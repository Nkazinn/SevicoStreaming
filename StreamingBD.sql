-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema streaming_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema streaming_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `streaming_db2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `streaming_db2` ;

-- -----------------------------------------------------
-- Table `streaming_db`.`administradores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`administradores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(90) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`moderadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`moderadores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(90) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_administrador_moderadores_idx` (`administrador_id` ASC) VISIBLE,
  CONSTRAINT `fk_administrador_moderadores`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `streaming_db2`.`administradores` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`pacotes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`pacotes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(90) NOT NULL,
  `valor` DECIMAL(7,2) NOT NULL,
  `descricao` VARCHAR(600) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(90) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `data_nascimento` DATE NULL,
  `possui_pacote` CHAR(3) NULL,
  `pacote_id` INT NULL,
  `tipo` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_pacote_usuarios_idx` (`pacote_id` ASC) VISIBLE,
  CONSTRAINT `fk_pacote_usuarios`
    FOREIGN KEY (`pacote_id`)
    REFERENCES `streaming_db2`.`pacotes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`usuarios_moderadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`usuarios_moderadores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `moderador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuarios_moderadores_moderadores1_idx` (`moderador_id` ASC) VISIBLE,
  INDEX `fk_usuario_usuariosmd_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_moderador_usuariosmd`
    FOREIGN KEY (`moderador_id`)
    REFERENCES `streaming_db`.`moderadores` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_usuariosmd`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `streaming_db2`.`usuarios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`conteudos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`conteudos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipoconteudo` VARCHAR(90) NOT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `quanti_episodio_etc` VARCHAR(30) NOT NULL,
  `data_lancamento` DATE NOT NULL,
  `autor` VARCHAR(90) NOT NULL,
  `descricao` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`avaliacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`avaliacoes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `conteudo_id` INT NOT NULL,
  `nota` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_avaliacao_conteudos1_idx` (`conteudo_id` ASC) VISIBLE,
  INDEX `fk_usuario_avaliacaes_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_conteudo_avaliacaes`
    FOREIGN KEY (`conteudo_id`)
    REFERENCES `streaming_db`.`conteudos` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_avaliacaes`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `streaming_db2`.`usuarios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`sugestoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`sugestoes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(90) NOT NULL,
  `data_hora` DATETIME NOT NULL,
  `descricao` VARCHAR(600) NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_sugestoes_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_sugestoes`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `streaming_db2`.`usuarios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`criticas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`criticas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `texto` TEXT NOT NULL,
  `data_hora` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_criticas_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_criticas`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `streaming_db2`.`usuarios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`usuarios_administradores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`usuarios_administradores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_usuariosad_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_administrador_usuariosad_idx` (`administrador_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_usuariosad`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `streaming_db`.`usuarios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_administrador_usuariosad`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `streaming_db2`.`administradores` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streaming_db`.`cartoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `streaming_db2`.`cartoes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(16) NOT NULL,
  `nome_titular` VARCHAR(90) NOT NULL,
  `validade` VARCHAR(7)  NOT NULL,
  `codigo_seguranca` VARCHAR(4) NOT NULL,
  `tipo` VARCHAR(20) NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cartoes_usuarios1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_cartoes`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `streaming_db2`.`usuarios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

INSERT INTO pacotes (nome, valor, descricao) VALUES 
('Pacote 1', 29.99, 'Pacote básico de streaming'),
('Pacote 2', 49.99, 'Pacote avançado de streaming'),
('Pacote 3', 69.99, 'Pacote premium de streaming');

INSERT INTO administradores (id, nome, senha, email) VALUES ('1', 'administrador', '81dc9bdb52d04dc20036dbd8313ed055', 'admin@gmail.com');
INSERT INTO usuarios (id, nome, senha, email,tipo) VALUES ('1','administrador',21,'ad@gmail.com','usuario');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
