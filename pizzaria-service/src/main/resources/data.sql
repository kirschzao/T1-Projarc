-- ============================================================
-- CLIENTES
-- Senha para todos: 123456 (criptografada com BCrypt)
-- ============================================================
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha, role) VALUES ('9001', 'Huguinho Pato', '51985744566', 'Rua das Flores, 100', 'huguinho.pato@email.com', '$2a$10$khY6KgjSCr2KsbfzbASDUOF4PMfTqJOAqYgJbakzqRd7ZZgSGP1Q6', 'CLIENTE') ON CONFLICT DO NOTHING;
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha, role) VALUES ('9002', 'Luizinho Pato', '51991720790', 'Av. Central, 200', 'zezinho.pato@email.com', '$2a$10$khY6KgjSCr2KsbfzbASDUOF4PMfTqJOAqYgJbakzqRd7ZZgSGP1Q6', 'CLIENTE') ON CONFLICT DO NOTHING;
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha, role) VALUES ('9003', 'Zezinho Pato', '51993456789', 'Rua Ipiranga, 350', 'luizinho.pato@email.com', '$2a$10$khY6KgjSCr2KsbfzbASDUOF4PMfTqJOAqYgJbakzqRd7ZZgSGP1Q6', 'CLIENTE') ON CONFLICT DO NOTHING;
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha, role) VALUES ('9004', 'Tio Patinhas', '51998765432', 'Caixa Forte, 1', 'patinhas@email.com', '$2a$10$khY6KgjSCr2KsbfzbASDUOF4PMfTqJOAqYgJbakzqRd7ZZgSGP1Q6', 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha, role) VALUES ('9005', 'Donald Pato', '51991234567', 'Rua dos Marrecos, 42', 'donald.pato@email.com', '$2a$10$khY6KgjSCr2KsbfzbASDUOF4PMfTqJOAqYgJbakzqRd7ZZgSGP1Q6', 'CLIENTE') ON CONFLICT DO NOTHING;
INSERT INTO clientes (cpf, nome, celular, endereco, email, senha, role) VALUES ('9006', 'Margarida Pato', '51994567890', 'Av. das Flores, 88', 'margarida@email.com', '$2a$10$khY6KgjSCr2KsbfzbASDUOF4PMfTqJOAqYgJbakzqRd7ZZgSGP1Q6', 'ADMIN') ON CONFLICT DO NOTHING;

-- ============================================================
-- CONFIGURACOES
-- ============================================================
INSERT INTO configuracoes (chave, valor) VALUES ('cardapio_corrente_id', '1') ON CONFLICT DO NOTHING;
INSERT INTO configuracoes (chave, valor) VALUES ('desconto_ativo', 'Fidelidade') ON CONFLICT DO NOTHING;

-- ============================================================
-- INGREDIENTES
-- ============================================================
INSERT INTO ingredientes (id, descricao) VALUES (1, 'Disco de pizza') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (2, 'Porcao de tomate') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (3, 'Porcao de mussarela') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (4, 'Porcao de presunto') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (5, 'Porcao de calabresa') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (6, 'Molho de tomate (200ml)') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (7, 'Porcao de azeitona') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (8, 'Porcao de oregano') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (9, 'Porcao de cebola') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (10, 'Porcao de frango desfiado') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (11, 'Porcao de catupiry') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (12, 'Porcao de bacon') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (13, 'Porcao de palmito') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (14, 'Porcao de milho') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (15, 'Porcao de rucula') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (16, 'Porcao de tomate seco') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (17, 'Porcao de chocolate') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (18, 'Porcao de morango') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (19, 'Lata de refrigerante') ON CONFLICT DO NOTHING;
INSERT INTO ingredientes (id, descricao) VALUES (20, 'Garrafa de suco') ON CONFLICT DO NOTHING;

-- ============================================================
-- RECEITAS (incluindo bebidas)
-- ============================================================
INSERT INTO receitas (id, titulo) VALUES (1, 'Pizza calabresa') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (2, 'Pizza queijo e presunto') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (3, 'Pizza margherita') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (4, 'Pizza frango com catupiry') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (5, 'Pizza portuguesa') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (6, 'Pizza bacon') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (7, 'Pizza palmito') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (8, 'Pizza rucula com tomate seco') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (9, 'Pizza chocolate com morango') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (10, 'Refrigerante lata') ON CONFLICT DO NOTHING;
INSERT INTO receitas (id, titulo) VALUES (11, 'Suco natural') ON CONFLICT DO NOTHING;

-- ============================================================
-- RECEITA <-> INGREDIENTES
-- ============================================================
-- Pizza calabresa
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 5) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 9) ON CONFLICT DO NOTHING;
-- Pizza queijo e presunto
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 4) ON CONFLICT DO NOTHING;
-- Pizza margherita
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 2) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 8) ON CONFLICT DO NOTHING;
-- Pizza frango com catupiry
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 10) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 11) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 14) ON CONFLICT DO NOTHING;
-- Pizza portuguesa
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 4) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 9) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 7) ON CONFLICT DO NOTHING;
-- Pizza bacon
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 12) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 9) ON CONFLICT DO NOTHING;
-- Pizza palmito
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 13) ON CONFLICT DO NOTHING;
-- Pizza rucula com tomate seco
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 15) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 16) ON CONFLICT DO NOTHING;
-- Pizza chocolate com morango
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (9, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (9, 17) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (9, 18) ON CONFLICT DO NOTHING;
-- Refrigerante lata
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (10, 19) ON CONFLICT DO NOTHING;
-- Suco natural
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (11, 20) ON CONFLICT DO NOTHING;

-- ============================================================
-- PRODUTOS (precos em centavos)
-- ============================================================
INSERT INTO produtos (id, descricao, preco) VALUES (1, 'Pizza calabresa', 5500) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (2, 'Pizza queijo e presunto', 6000) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (3, 'Pizza margherita', 4000) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (4, 'Pizza frango com catupiry', 6500) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (5, 'Pizza portuguesa', 5800) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (6, 'Pizza bacon', 6200) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (7, 'Pizza palmito', 5700) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (8, 'Pizza rucula com tomate seco', 7000) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (9, 'Pizza chocolate com morango', 5000) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (10, 'Refrigerante lata', 800) ON CONFLICT DO NOTHING;
INSERT INTO produtos (id, descricao, preco) VALUES (11, 'Suco natural', 1200) ON CONFLICT DO NOTHING;

-- ============================================================
-- PRODUTO <-> RECEITA (incluindo bebidas)
-- ============================================================
INSERT INTO produto_receita (produto_id, receita_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (2, 2) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (3, 3) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (4, 4) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (5, 5) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (6, 6) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (7, 7) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (8, 8) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (9, 9) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (10, 10) ON CONFLICT DO NOTHING;
INSERT INTO produto_receita (produto_id, receita_id) VALUES (11, 11) ON CONFLICT DO NOTHING;

-- ============================================================
-- CARDAPIOS
-- ============================================================
INSERT INTO cardapios (id, titulo) VALUES (1, 'Cardapio Classico') ON CONFLICT DO NOTHING;
INSERT INTO cardapios (id, titulo) VALUES (2, 'Cardapio Gourmet') ON CONFLICT DO NOTHING;
INSERT INTO cardapios (id, titulo) VALUES (3, 'Cardapio Promocional') ON CONFLICT DO NOTHING;

-- ============================================================
-- CARDAPIO <-> PRODUTO
-- ============================================================
-- Cardapio Classico: todas as pizzas tradicionais + bebidas
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 2) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 3) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 4) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 5) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 6) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 10) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (1, 11) ON CONFLICT DO NOTHING;
-- Cardapio Gourmet: pizzas especiais + bebidas
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 4) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 7) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 8) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 9) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (2, 11) ON CONFLICT DO NOTHING;
-- Cardapio Promocional: selecao com desconto
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (3, 1) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (3, 3) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (3, 5) ON CONFLICT DO NOTHING;
INSERT INTO cardapio_produto (cardapio_id, produto_id) VALUES (3, 10) ON CONFLICT DO NOTHING;

-- ============================================================
-- PEDIDOS HISTORICOS (para testar UC5, UC8, UC9 e descontos)
-- ============================================================
-- Huguinho: 5 pedidos entregues (elegivel para desconto: > 3 nos ultimos 20 dias)
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (1, '9001', 'ENTREGUE', 11000, 1100, 0, 12100, CURRENT_TIMESTAMP - INTERVAL '18 days') ON CONFLICT DO NOTHING;
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (2, '9001', 'ENTREGUE', 6000, 600, 0, 6600, CURRENT_TIMESTAMP - INTERVAL '15 days') ON CONFLICT DO NOTHING;
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (3, '9001', 'ENTREGUE', 5500, 550, 0, 6050, CURRENT_TIMESTAMP - INTERVAL '10 days') ON CONFLICT DO NOTHING;
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (4, '9001', 'ENTREGUE', 12500, 1250, 875, 12875, CURRENT_TIMESTAMP - INTERVAL '5 days') ON CONFLICT DO NOTHING;
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (5, '9001', 'ENTREGUE', 4000, 400, 280, 4120, CURRENT_TIMESTAMP - INTERVAL '2 days') ON CONFLICT DO NOTHING;
-- Luizinho: 2 pedidos entregues + 1 aprovado (sem desconto)
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (6, '9002', 'ENTREGUE', 5500, 550, 0, 6050, CURRENT_TIMESTAMP - INTERVAL '12 days') ON CONFLICT DO NOTHING;
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (7, '9002', 'ENTREGUE', 6200, 620, 0, 6820, CURRENT_TIMESTAMP - INTERVAL '7 days') ON CONFLICT DO NOTHING;
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (8, '9002', 'APROVADO', 10000, 1000, 0, 11000, CURRENT_TIMESTAMP - INTERVAL '1 days') ON CONFLICT DO NOTHING;
-- Zezinho: 1 pedido entregue + 1 cancelado
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (9, '9003', 'ENTREGUE', 7000, 700, 0, 7700, CURRENT_TIMESTAMP - INTERVAL '8 days') ON CONFLICT DO NOTHING;
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (10, '9003', 'CANCELADO', 5800, 580, 0, 6380, CURRENT_TIMESTAMP - INTERVAL '3 days') ON CONFLICT DO NOTHING;
-- Tio Patinhas: 3 pedidos entregues (exatamente 3, NAO elegivel para desconto pois precisa de mais de 3)
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (11, '9004', 'ENTREGUE', 6500, 650, 0, 7150, CURRENT_TIMESTAMP - INTERVAL '14 days') ON CONFLICT DO NOTHING;
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (12, '9004', 'ENTREGUE', 5700, 570, 0, 6270, CURRENT_TIMESTAMP - INTERVAL '9 days') ON CONFLICT DO NOTHING;
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (13, '9004', 'ENTREGUE', 11500, 1150, 0, 12650, CURRENT_TIMESTAMP - INTERVAL '4 days') ON CONFLICT DO NOTHING;
-- Donald: 1 pedido entregue antigo (fora dos 20 dias)
INSERT INTO pedidos (id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao) VALUES (14, '9005', 'ENTREGUE', 4000, 400, 0, 4400, CURRENT_TIMESTAMP - INTERVAL '30 days') ON CONFLICT DO NOTHING;

-- Ajustar sequence para não conflitar com novos pedidos
SELECT setval('pedidos_id_seq', 14, true);

-- ============================================================
-- ITENS DOS PEDIDOS HISTORICOS
-- ============================================================
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (1, 1, 1, 2) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (2, 2, 2, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (3, 3, 1, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (4, 4, 4, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (5, 4, 6, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (6, 5, 3, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (7, 6, 1, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (8, 7, 6, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (9, 8, 3, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (10, 8, 2, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (11, 9, 8, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (12, 10, 5, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (13, 11, 4, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (14, 12, 7, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (15, 13, 1, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (16, 13, 6, 1) ON CONFLICT DO NOTHING;
INSERT INTO pedido_itens (id, pedido_id, produto_id, quantidade) VALUES (17, 14, 3, 1) ON CONFLICT DO NOTHING;

SELECT setval('pedido_itens_id_seq', 17, true);

-- ============================================================
-- HISTORICO DE STATUS DOS PEDIDOS
-- ============================================================
-- Pedidos entregues: NOVO -> APROVADO -> PAGO -> AGUARDANDO -> PREPARACAO -> PRONTO -> TRANSPORTE -> ENTREGUE
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (1, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '17 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (2, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '14 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (3, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '9 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (4, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '4 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (5, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '1 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (6, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '11 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (7, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '6 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (9, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '7 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (11, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '13 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (12, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '8 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (13, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '3 days') ON CONFLICT DO NOTHING;
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (14, 'ENTREGUE', CURRENT_TIMESTAMP - INTERVAL '29 days') ON CONFLICT DO NOTHING;
-- Pedido 8: aprovado (sem historico de entrega)
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (8, 'APROVADO', CURRENT_TIMESTAMP - INTERVAL '1 days') ON CONFLICT DO NOTHING;
-- Pedido 10: cancelado
INSERT INTO pedido_historico_status (pedido_id, status, data_hora) VALUES (10, 'CANCELADO', CURRENT_TIMESTAMP - INTERVAL '3 days') ON CONFLICT DO NOTHING;
