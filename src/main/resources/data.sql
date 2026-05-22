-- ============================================================
-- CLIENTES
-- Senha para todos: 123456 (criptografada com BCrypt)
-- ============================================================
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha) VALUES ('9001', 'Huguinho Pato', '51985744566', 'Rua das Flores, 100', 'huguinho.pato@email.com', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ss7KIUgO2t0jWMUga');
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha) VALUES ('9002', 'Luizinho Pato', '51991720790', 'Av. Central, 200', 'zezinho.pato@email.com', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ss7KIUgO2t0jWMUga');
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha) VALUES ('9003', 'Zezinho Pato', '51993456789', 'Rua Ipiranga, 350', 'luizinho.pato@email.com', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ss7KIUgO2t0jWMUga');
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha) VALUES ('9004', 'Tio Patinhas', '51998765432', 'Caixa Forte, 1', 'patinhas@email.com', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ss7KIUgO2t0jWMUga');
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha) VALUES ('9005', 'Donald Pato', '51991234567', 'Rua dos Marrecos, 42', 'donald.pato@email.com', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ss7KIUgO2t0jWMUga');
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha) VALUES ('9006', 'Margarida Pato', '51994567890', 'Av. das Flores, 88', 'margarida@email.com', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ss7KIUgO2t0jWMUga');

-- ============================================================
-- INGREDIENTES
-- ============================================================
INSERT INTO ingredientes (id, descricao) VALUES (1, 'Disco de pizza');
INSERT INTO ingredientes (id, descricao) VALUES (2, 'Porcao de tomate');
INSERT INTO ingredientes (id, descricao) VALUES (3, 'Porcao de mussarela');
INSERT INTO ingredientes (id, descricao) VALUES (4, 'Porcao de presunto');
INSERT INTO ingredientes (id, descricao) VALUES (5, 'Porcao de calabresa');
INSERT INTO ingredientes (id, descricao) VALUES (6, 'Molho de tomate (200ml)');
INSERT INTO ingredientes (id, descricao) VALUES (7, 'Porcao de azeitona');
INSERT INTO ingredientes (id, descricao) VALUES (8, 'Porcao de oregano');
INSERT INTO ingredientes (id, descricao) VALUES (9, 'Porcao de cebola');
INSERT INTO ingredientes (id, descricao) VALUES (10, 'Porcao de frango desfiado');
INSERT INTO ingredientes (id, descricao) VALUES (11, 'Porcao de catupiry');
INSERT INTO ingredientes (id, descricao) VALUES (12, 'Porcao de bacon');
INSERT INTO ingredientes (id, descricao) VALUES (13, 'Porcao de palmito');
INSERT INTO ingredientes (id, descricao) VALUES (14, 'Porcao de milho');
INSERT INTO ingredientes (id, descricao) VALUES (15, 'Porcao de rucula');
INSERT INTO ingredientes (id, descricao) VALUES (16, 'Porcao de tomate seco');
INSERT INTO ingredientes (id, descricao) VALUES (17, 'Porcao de chocolate');
INSERT INTO ingredientes (id, descricao) VALUES (18, 'Porcao de morango');
INSERT INTO ingredientes (id, descricao) VALUES (19, 'Lata de refrigerante');
INSERT INTO ingredientes (id, descricao) VALUES (20, 'Garrafa de suco');

-- ============================================================
-- ITENS DE ESTOQUE
-- ============================================================
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (1, 50, 1);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (2, 40, 2);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (3, 60, 3);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (4, 35, 4);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (5, 40, 5);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (6, 50, 6);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (7, 30, 7);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (8, 45, 8);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (9, 35, 9);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (10, 30, 10);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (11, 25, 11);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (12, 30, 12);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (13, 20, 13);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (14, 25, 14);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (15, 20, 15);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (16, 15, 16);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (17, 20, 17);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (18, 15, 18);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (19, 100, 19);
INSERT INTO itensEstoque (id, quantidade, ingrediente_id) VALUES (20, 50, 20);

-- ============================================================
-- RECEITAS
-- ============================================================
INSERT INTO receitas (id, titulo) VALUES (1, 'Pizza calabresa');
INSERT INTO receitas (id, titulo) VALUES (2, 'Pizza queijo e presunto');
INSERT INTO receitas (id, titulo) VALUES (3, 'Pizza margherita');
INSERT INTO receitas (id, titulo) VALUES (4, 'Pizza frango com catupiry');
INSERT INTO receitas (id, titulo) VALUES (5, 'Pizza portuguesa');
INSERT INTO receitas (id, titulo) VALUES (6, 'Pizza bacon');
INSERT INTO receitas (id, titulo) VALUES (7, 'Pizza palmito');
INSERT INTO receitas (id, titulo) VALUES (8, 'Pizza rucula com tomate seco');
INSERT INTO receitas (id, titulo) VALUES (9, 'Pizza chocolate com morango');

-- ============================================================
-- RECEITA <-> INGREDIENTES
-- ============================================================
-- Pizza calabresa
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 1);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 6);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 3);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 5);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 9);
-- Pizza queijo e presunto
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 1);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 6);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 3);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 4);
-- Pizza margherita
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 1);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 6);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 3);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 2);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 8);
-- Pizza frango com catupiry
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 1);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 6);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 10);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 11);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 14);
-- Pizza portuguesa
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 1);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 6);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 3);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 4);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 9);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 7);
-- Pizza bacon
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 1);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 6);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 3);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 12);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 9);
-- Pizza palmito
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 1);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 6);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 3);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 13);
-- Pizza rucula com tomate seco
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 1);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 3);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 15);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 16);
-- Pizza chocolate com morango
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (9, 1);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (9, 17);
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (9, 18);

-- ============================================================
-- PRODUTOS (precos em centavos)
-- ============================================================
INSERT INTO produtos (id, descricao, preco) VALUES (1, 'Pizza calabresa', 5500);
INSERT INTO produtos (id, descricao, preco) VALUES (2, 'Pizza queijo e presunto', 6000);
INSERT INTO produtos (id, descricao, preco) VALUES (3, 'Pizza margherita', 4000);
INSERT INTO produtos (id, descricao, preco) VALUES (4, 'Pizza frango com catupiry', 6500);
INSERT INTO produtos (id, descricao, preco) VALUES (5, 'Pizza portuguesa', 5800);
INSERT INTO produtos (id, descricao, preco) VALUES (6, 'Pizza bacon', 6200);
INSERT INTO produtos (id, descricao, preco) VALUES (7, 'Pizza palmito', 5700);
INSERT INTO produtos (id, descricao, preco) VALUES (8, 'Pizza rucula com tomate seco', 7000);
INSERT INTO produtos (id, descricao, preco) VALUES (9, 'Pizza chocolate com morango', 5000);
INSERT INTO produtos (id, descricao, preco) VALUES (10, 'Refrigerante lata', 800);
INSERT INTO produtos (id, descricao, preco) VALUES (11, 'Suco natural', 1200);

-- ============================================================
-- PRODUTO <-> RECEITA
-- ============================================================
INSERT INTO produto_receita (produto_id, receita_id) VALUES (1, 1);
INSERT INTO produto_receita (produto_id, receita_id) VALUES (2, 2);
INSERT INTO produto_receita (produto_id, receita_id) VALUES (3, 3);
INSERT INTO produto_receita (produto_id, receita_id) VALUES (4, 4);
INSERT INTO produto_receita (produto_id, receita_id) VALUES (5, 5);
INSERT INTO produto_receita (produto_id, receita_id) VALUES (6, 6);
INSERT INTO produto_receita (produto_id, receita_id) VALUES (7, 7);
INSERT INTO produto_receita (produto_id, receita_id) VALUES (8, 8);
INSERT INTO produto_receita (produto_id, receita_id) VALUES (9, 9);

-- ============================================================
-- CARDAPIOS
-- ============================================================
INSERT INTO cardapios (id, titulo) VALUES (1, 'Cardapio Classico');
INSERT INTO cardapios (id, titulo) VALUES (2, 'Cardapio Gourmet');
INSERT INTO cardapios (id, titulo) VALUES (3, 'Cardapio Promocional');

-- ============================================================
-- CARDAPIO <-> PRODUTO
-- ============================================================
-- Cardapio Classico: todas as pizzas tradicionais + bebidas
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 1);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 2);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 3);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 4);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 5);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 6);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 10);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 11);

-- Cardapio Gourmet: pizzas especiais + bebidas
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 4);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 7);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 8);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 9);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 11);

-- Cardapio Promocional: selecao com desconto
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (3, 1);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (3, 3);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (3, 5);
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (3, 10);

-- ============================================================
-- PEDIDOS HISTORICOS (para testar UC5, UC8, UC9 e descontos)
-- ============================================================
-- Huguinho: 5 pedidos entregues (elegivel para desconto: > 3 nos ultimos 20 dias)
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9001', 'ENTREGUE', 11000, 1100, 0, 12100, DATEADD('DAY', -18, CURRENT_TIMESTAMP));
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9001', 'ENTREGUE', 6000, 600, 0, 6600, DATEADD('DAY', -15, CURRENT_TIMESTAMP));
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9001', 'ENTREGUE', 5500, 550, 0, 6050, DATEADD('DAY', -10, CURRENT_TIMESTAMP));
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9001', 'ENTREGUE', 12500, 1250, 875, 12875, DATEADD('DAY', -5, CURRENT_TIMESTAMP));
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9001', 'ENTREGUE', 4000, 400, 280, 4120, DATEADD('DAY', -2, CURRENT_TIMESTAMP));

-- Luizinho: 2 pedidos entregues + 1 aprovado (sem desconto)
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9002', 'ENTREGUE', 5500, 550, 0, 6050, DATEADD('DAY', -12, CURRENT_TIMESTAMP));
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9002', 'ENTREGUE', 6200, 620, 0, 6820, DATEADD('DAY', -7, CURRENT_TIMESTAMP));
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9002', 'APROVADO', 10000, 1000, 0, 11000, DATEADD('DAY', -1, CURRENT_TIMESTAMP));

-- Zezinho: 1 pedido entregue + 1 cancelado
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9003', 'ENTREGUE', 7000, 700, 0, 7700, DATEADD('DAY', -8, CURRENT_TIMESTAMP));
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9003', 'CANCELADO', 5800, 580, 0, 6380, DATEADD('DAY', -3, CURRENT_TIMESTAMP));

-- Tio Patinhas: 3 pedidos entregues (exatamente 3, NAO elegivel para desconto pois precisa de mais de 3)
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9004', 'ENTREGUE', 6500, 650, 0, 7150, DATEADD('DAY', -14, CURRENT_TIMESTAMP));
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9004', 'ENTREGUE', 5700, 570, 0, 6270, DATEADD('DAY', -9, CURRENT_TIMESTAMP));
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9004', 'ENTREGUE', 11500, 1150, 0, 12650, DATEADD('DAY', -4, CURRENT_TIMESTAMP));

-- Donald: 1 pedido entregue antigo (fora dos 20 dias)
INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES ('9005', 'ENTREGUE', 4000, 400, 0, 4400, DATEADD('DAY', -30, CURRENT_TIMESTAMP));

-- Margarida: sem pedidos (cliente novo)

-- ============================================================
-- ITENS DOS PEDIDOS HISTORICOS
-- ============================================================
-- Pedido 1 (Huguinho): 2x calabresa
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (1, 1, 2);
-- Pedido 2 (Huguinho): 1x queijo e presunto
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (2, 2, 1);
-- Pedido 3 (Huguinho): 1x calabresa
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (3, 1, 1);
-- Pedido 4 (Huguinho): 1x frango catupiry + 1x bacon
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (4, 4, 1);
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (4, 6, 1);
-- Pedido 5 (Huguinho): 1x margherita
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (5, 3, 1);
-- Pedido 6 (Luizinho): 1x calabresa
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (6, 1, 1);
-- Pedido 7 (Luizinho): 1x bacon
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (7, 6, 1);
-- Pedido 8 (Luizinho): 1x margherita + 1x queijo presunto (aprovado, aguardando pagamento)
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (8, 3, 1);
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (8, 2, 1);
-- Pedido 9 (Zezinho): 1x rucula tomate seco
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (9, 8, 1);
-- Pedido 10 (Zezinho): 1x portuguesa (cancelado)
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (10, 5, 1);
-- Pedido 11 (Patinhas): 1x frango catupiry
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (11, 4, 1);
-- Pedido 12 (Patinhas): 1x palmito
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (12, 7, 1);
-- Pedido 13 (Patinhas): 1x calabresa + 1x bacon
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (13, 1, 1);
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (13, 6, 1);
-- Pedido 14 (Donald): 1x margherita (antigo)
INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (14, 3, 1);