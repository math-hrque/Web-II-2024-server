CREATE TABLE funcionario (
	id_funcionario INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	data_nascimento DATE NOT NULL,
	id_usuario INTEGER NOT NULL,
	CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);