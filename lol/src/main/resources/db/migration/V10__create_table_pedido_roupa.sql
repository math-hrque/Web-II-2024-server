CREATE TABLE pedido_roupa (
	id_pedido_roupa INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	quantidade INTEGER NOT NULL,
	id_pedido INTEGER NOT NULL,
	id_roupa INTEGER NOT NULL,
	CONSTRAINT fk_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
	CONSTRAINT fk_roupa FOREIGN KEY (id_roupa) REFERENCES roupa(id_roupa)
);