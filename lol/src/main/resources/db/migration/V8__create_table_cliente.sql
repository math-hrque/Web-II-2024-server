CREATE TABLE cliente (
	id_cliente INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	cpf VARCHAR(11) NOT NULL UNIQUE,
	telefone VARCHAR(11) NOT NULL,
	id_usuario INTEGER NOT NULL,
	id_endereco INTEGER NOT NULL,
	CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
	CONSTRAINT fk_endereco FOREIGN KEY (id_endereco) REFERENCES endereco(id_endereco)
);