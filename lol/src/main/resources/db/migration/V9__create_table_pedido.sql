CREATE SEQUENCE numero_pedido_sequencia START 10000;
CREATE TABLE pedido (
	id_pedido INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	numero_pedido INTEGER DEFAULT nextval('numero_pedido_sequencia') UNIQUE,
	data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	data_pagamento TIMESTAMP,
	id_cliente INTEGER NOT NULL,
	id_situacao INTEGER NOT NULL,
	id_orcamento INTEGER NOT NULL,
	CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
	CONSTRAINT fk_situacao FOREIGN KEY (id_situacao) REFERENCES situacao(id_situacao),
	CONSTRAINT fk_orcamento FOREIGN KEY (id_orcamento) REFERENCES orcamento(id_orcamento)
);