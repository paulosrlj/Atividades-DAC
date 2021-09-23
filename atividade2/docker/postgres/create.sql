CREATE TABLE clientes(
    nome varchar (25), 
    cpf varchar (25),
    id serial,
    PRIMARY KEY (id)
);

CREATE TABLE produtos(
    descricao varchar (150), 
    valor decimal,
    id serial,
    PRIMARY KEY (id)
);

CREATE TABLE venda(
    id serial,
    cliente_id int,

    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE produto_venda(
    id serial,
    venda_id int,
    produto_id int,
    
    PRIMARY KEY (id),
    FOREIGN KEY (venda_id) REFERENCES venda(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
