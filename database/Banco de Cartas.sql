CREATE DATABASE IF NOT EXISTS jogo_cartas;
USE jogo_cartas;

CREATE TABLE IF NOT EXISTS cartas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    atk INT,
    def INT,
    bonus INT
);
