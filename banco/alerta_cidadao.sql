-- FIAP Global Solution 2026/2 - Alerta Cidadao
-- Modelo alinhado a model/Regiao.java, Cidadao.java, Leitura.java e Alerta.java
-- Requer MySQL 8.0.16+ para aplicacao de CHECK.
-- ATENCAO: DROP TABLE remove dados existentes das quatro tabelas do projeto.
CREATE DATABASE IF NOT EXISTS alerta_cidadao CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE alerta_cidadao;

DROP TABLE IF EXISTS alerta;
DROP TABLE IF EXISTS leitura;
DROP TABLE IF EXISTS cidadao;
DROP TABLE IF EXISTS regiao;

CREATE TABLE regiao (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  estado CHAR(2) NOT NULL,
  area_de_risco BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT uq_regiao UNIQUE (nome, cidade, estado)
) ENGINE=InnoDB;

CREATE TABLE cidadao (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  email VARCHAR(150) NOT NULL,
  id_regiao INT NOT NULL,
  CONSTRAINT uq_cidadao_email UNIQUE (email),
  CONSTRAINT fk_cidadao_regiao FOREIGN KEY (id_regiao) REFERENCES regiao(id)
) ENGINE=InnoDB;

CREATE TABLE leitura (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_regiao INT NOT NULL,
  tipo VARCHAR(20) NOT NULL,
  valor DOUBLE NOT NULL,
  data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT chk_leitura_tipo CHECK (tipo IN ('CHUVA','NIVEL_RIO','TEMPERATURA','UMIDADE')),
  CONSTRAINT chk_leitura_valor CHECK (
    (tipo = 'CHUVA' AND valor BETWEEN 0 AND 500) OR
    (tipo = 'NIVEL_RIO' AND valor BETWEEN 0 AND 30) OR
    (tipo = 'TEMPERATURA' AND valor BETWEEN -30 AND 60) OR
    (tipo = 'UMIDADE' AND valor BETWEEN 0 AND 100)
  ),
  CONSTRAINT fk_leitura_regiao FOREIGN KEY (id_regiao) REFERENCES regiao(id)
) ENGINE=InnoDB;

CREATE TABLE alerta (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_regiao INT NOT NULL,
  tipo_evento VARCHAR(20) NOT NULL,
  nivel VARCHAR(10) NOT NULL,
  descricao VARCHAR(500) NOT NULL,
  data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ativo BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT chk_alerta_tipo CHECK (tipo_evento IN ('ENCHENTE','DESLIZAMENTO','INCENDIO','ONDA_CALOR')),
  CONSTRAINT chk_alerta_nivel CHECK (nivel IN ('BAIXO','MEDIO','ALTO','CRITICO')),
  CONSTRAINT fk_alerta_regiao FOREIGN KEY (id_regiao) REFERENCES regiao(id)
) ENGINE=InnoDB;

-- Mesmas regioes, cidadaos e leituras de DadosExemplo.java.
INSERT INTO regiao (nome,cidade,estado,area_de_risco) VALUES
('Centro Histórico','Porto Alegre','RS',FALSE),
('Sarandi','Porto Alegre','RS',TRUE),
('Alto da Serra','Petrópolis','RJ',TRUE),
('Zona Rural','Cuiabá','MT',FALSE);

INSERT INTO cidadao (nome,telefone,email,id_regiao) VALUES
('Maria Souza','51999990001','maria@email.com',1),
('João Pereira','51999990002','joao@email.com',2),
('Ana Lima','51999990003','ana@email.com',2),
('Carlos Rocha','24999990004','carlos@email.com',3),
('Fernanda Alves','65999990005','fernanda@email.com',4);

INSERT INTO leitura (id_regiao,tipo,valor,data_hora) VALUES
(1,'TEMPERATURA',31.5,'2026-11-19 10:00:00'),
(2,'CHUVA',65,'2026-11-19 10:05:00'),
(3,'CHUVA',20,'2026-11-19 10:10:00'),
(4,'UMIDADE',18,'2026-11-19 10:15:00');

-- Alertas que as regras Java gerariam com as leituras acima.
INSERT INTO alerta (id_regiao,tipo_evento,nivel,descricao,data_hora,ativo) VALUES
(2,'ENCHENTE','ALTO','Chuva forte de 65.0 mm','2026-11-19 10:05:00',TRUE),
(2,'DESLIZAMENTO','ALTO','Solo encharcado em área de risco (65.0 mm)','2026-11-19 10:05:00',TRUE),
(4,'INCENDIO','ALTO','Umidade do ar em 18.0%','2026-11-19 10:15:00',TRUE);

-- Conferencia: resultados esperados = 4 regioes, 5 cidadaos, 4 leituras, 3 alertas.
SELECT 'regiao' AS tabela, COUNT(*) AS registros FROM regiao
UNION ALL SELECT 'cidadao', COUNT(*) FROM cidadao
UNION ALL SELECT 'leitura', COUNT(*) FROM leitura
UNION ALL SELECT 'alerta', COUNT(*) FROM alerta;

-- Consulta JOIN para demonstracao
SELECT a.id, a.tipo_evento, a.nivel, a.descricao, a.ativo,
       r.nome AS regiao, r.cidade, r.estado
FROM alerta a
INNER JOIN regiao r ON r.id = a.id_regiao
ORDER BY a.id;
