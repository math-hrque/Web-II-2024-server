CREATE TABLE orcamento (
	id_orcamento INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	valor NUMERIC(10,2) NOT NULL,
	data_prazo DATE NOT NULL,
	aprovado boolean NOT NULL
);