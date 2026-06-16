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
-- ITENS DE ESTOQUE
-- ============================================================
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (1, 50, 1) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (2, 40, 2) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (3, 60, 3) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (4, 35, 4) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (5, 40, 5) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (6, 50, 6) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (7, 30, 7) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (8, 45, 8) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (9, 35, 9) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (10, 30, 10) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (11, 25, 11) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (12, 30, 12) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (13, 20, 13) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (14, 25, 14) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (15, 20, 15) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (16, 15, 16) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (17, 20, 17) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (18, 15, 18) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (19, 100, 19) ON CONFLICT DO NOTHING;
INSERT INTO itens_estoque (id, quantidade, ingrediente_id) VALUES (20, 50, 20) ON CONFLICT DO NOTHING;

-- ============================================================
-- RECEITAS
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
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 5) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (1, 9) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (2, 4) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 2) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (3, 8) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 10) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 11) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (4, 14) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 4) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 9) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (5, 7) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 12) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (6, 9) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 6) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (7, 13) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 3) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 15) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (8, 16) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (9, 1) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (9, 17) ON CONFLICT DO NOTHING;
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (9, 18) ON CONFLICT DO NOTHING;
-- Refrigerante lata
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (10, 19) ON CONFLICT DO NOTHING;
-- Suco natural
INSERT INTO receita_ingrediente (receita_id, ingrediente_id) VALUES (11, 20) ON CONFLICT DO NOTHING;
