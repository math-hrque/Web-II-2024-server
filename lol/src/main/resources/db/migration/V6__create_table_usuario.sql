CREATE TABLE usuario (
	id_usuario INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	email VARCHAR(100) NOT NULL UNIQUE,
	senha VARCHAR(100) NOT NULL,
	id_permissao INTEGER NOT NULL,
    CONSTRAINT fk_permissao FOREIGN KEY (id_permissao) REFERENCES permissao(id_permissao)
);