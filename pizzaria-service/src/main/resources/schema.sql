CREATE TABLE IF NOT EXISTS clientes(
  cpf VARCHAR(15) NOT NULL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  celular VARCHAR(20) NOT NULL,
  endereco VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  senha VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ingredientes (
  id BIGINT PRIMARY KEY,
  descricao VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS itensEstoque(
    id BIGINT PRIMARY KEY,
    quantidade INT,
    ingrediente_id BIGINT,
    FOREIGN KEY (ingrediente_id) REFERENCES ingredientes(id)
);

CREATE TABLE IF NOT EXISTS receitas (
  id BIGINT PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS receita_ingrediente (
  receita_id BIGINT NOT NULL,
  ingrediente_id BIGINT NOT NULL,
  PRIMARY KEY (receita_id, ingrediente_id),
  FOREIGN KEY (receita_id) REFERENCES receitas(id),
  FOREIGN KEY (ingrediente_id) REFERENCES ingredientes(id)
);

CREATE TABLE IF NOT EXISTS produtos (
  id BIGINT PRIMARY KEY,
  descricao VARCHAR(255) NOT NULL,
  preco BIGINT,
  disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS produto_receita (
  produto_id BIGINT NOT NULL,
  receita_id BIGINT NOT NULL,
  PRIMARY KEY (produto_id, receita_id),
  FOREIGN KEY (produto_id) REFERENCES produtos(id),
  FOREIGN KEY (receita_id) REFERENCES receitas(id)
);

CREATE TABLE IF NOT EXISTS cardapios (
  id BIGINT PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cardapio_produto (
  cardapio_id BIGINT NOT NULL,
  produto_id BIGINT NOT NULL,
  PRIMARY KEY (cardapio_id, produto_id),
  FOREIGN KEY (cardapio_id) REFERENCES cardapios(id),
  FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

CREATE TABLE IF NOT EXISTS pedidos (
  id BIGSERIAL PRIMARY KEY,
  cliente_cpf VARCHAR(15) NOT NULL,
  status VARCHAR(50) NOT NULL DEFAULT 'NOVO',
  valor DOUBLE PRECISION NOT NULL DEFAULT 0,
  impostos DOUBLE PRECISION NOT NULL DEFAULT 0,
  desconto DOUBLE PRECISION NOT NULL DEFAULT 0,
  valor_cobrado DOUBLE PRECISION NOT NULL DEFAULT 0,
  data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (cliente_cpf) REFERENCES clientes(cpf)
);

CREATE TABLE IF NOT EXISTS pedido_itens (
  id BIGSERIAL PRIMARY KEY,
  pedido_id BIGINT NOT NULL,
  produto_id BIGINT NOT NULL,
  quantidade INT NOT NULL,
  FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
  FOREIGN KEY (produto_id) REFERENCES produtos(id)
);
