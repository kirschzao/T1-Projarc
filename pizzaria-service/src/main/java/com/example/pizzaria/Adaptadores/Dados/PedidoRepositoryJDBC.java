package com.example.pizzaria.Adaptadores.Dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.pizzaria.Dominio.Dados.ClienteRepository;
import com.example.pizzaria.Dominio.Dados.PedidoRepository;
import com.example.pizzaria.Dominio.Dados.ProdutosRepository;
import com.example.pizzaria.Dominio.Entidades.Cliente;
import com.example.pizzaria.Dominio.Entidades.ItemPedido;
import com.example.pizzaria.Dominio.Entidades.Pedido;
import com.example.pizzaria.Dominio.Entidades.Produto;

@Repository
public class PedidoRepositoryJDBC implements PedidoRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProdutosRepository produtosRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public PedidoRepositoryJDBC(JdbcTemplate jdbcTemplate, ProdutosRepository produtosRepository, ClienteRepository clienteRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.produtosRepository = produtosRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (cliente_cpf, status, valor, impostos, desconto, valor_cobrado) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            var ps = conn.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, pedido.getCliente().getCpf());
            ps.setString(2, pedido.getStatus().name());
            ps.setDouble(3, pedido.getValor());
            ps.setDouble(4, pedido.getImpostos());
            ps.setDouble(5, pedido.getDesconto());
            ps.setDouble(6, pedido.getValorCobrado());
            return ps;
        }, keyHolder);

        Long novoId = keyHolder.getKey().longValue();

        // Salvar itens do pedido
        for (ItemPedido item : pedido.getItens()) {
            String sqlItem = "INSERT INTO pedido_itens (pedido_id, produto_id, quantidade) VALUES (?, ?, ?)";
            jdbcTemplate.update(sqlItem, novoId, item.getItem().getId(), item.getQuantidade());
        }

        // Retornar o pedido com o novo ID
        return new Pedido(
            novoId,
            pedido.getCliente(),
            pedido.getItens(),
            pedido.getStatus(),
            pedido.getValor(),
            pedido.getImpostos(),
            pedido.getDesconto(),
            pedido.getValorCobrado(),
            java.time.LocalDateTime.now()
        );
    }

    @Override
    public Pedido recuperarPorId(long id) {
        String sql = "SELECT id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao " +
                     "FROM pedidos WHERE id = ?";
        
        List<Pedido> pedidos = jdbcTemplate.query(sql, ps -> ps.setLong(1, id), 
            (rs, rowNum) -> mapearPedido(rs));
        
        return pedidos.isEmpty() ? null : pedidos.getFirst();
    }

    @Override
    public void atualizar(Pedido pedido) {
        String sql = "UPDATE pedidos SET status = ? WHERE id = ?";
        
        jdbcTemplate.update(sql,
            pedido.getStatus().name(),
            pedido.getId()
        );
    }

    @Override
    public int contarPedidosPorClienteNoPeriodo(String cpf, int dias) {
        String sql = "SELECT COUNT(*) FROM pedidos WHERE cliente_cpf = ? AND data_criacao >= DATEADD('DAY', ?, CURRENT_TIMESTAMP)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, cpf, -dias);
        return count != null ? count : 0;
    }

    @Override
    public List<Pedido> recuperarEntreguesPorData(java.time.LocalDateTime inicio, java.time.LocalDateTime fim) {
        String sql = "SELECT id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao " +
                     "FROM pedidos WHERE status = 'ENTREGUE' AND data_criacao BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, ps -> {
            ps.setObject(1, inicio);
            ps.setObject(2, fim);
        }, (rs, rowNum) -> mapearPedido(rs));
    }

    @Override
    public List<Pedido> recuperarEntreguesPorClienteEData(String cpf, java.time.LocalDateTime inicio, java.time.LocalDateTime fim) {
        String sql = "SELECT id, cliente_cpf, status, valor, impostos, desconto, valor_cobrado, data_criacao " +
                     "FROM pedidos WHERE cliente_cpf = ? AND status = 'ENTREGUE' AND data_criacao BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, cpf);
            ps.setObject(2, inicio);
            ps.setObject(3, fim);
        }, (rs, rowNum) -> mapearPedido(rs));
    }

    private Pedido mapearPedido(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String clienteCpf = rs.getString("cliente_cpf");
        Pedido.Status status = Pedido.Status.valueOf(rs.getString("status"));
        double valor = rs.getDouble("valor");
        double impostos = rs.getDouble("impostos");
        double desconto = rs.getDouble("desconto");
        double valorCobrado = rs.getDouble("valor_cobrado");
        java.time.LocalDateTime dataCriacao = rs.getTimestamp("data_criacao").toLocalDateTime();

        // Recuperar itens do pedido
        String sqlItens = "SELECT produto_id, quantidade FROM pedido_itens WHERE pedido_id = ?";
        List<ItemPedido> itens = jdbcTemplate.query(sqlItens,
            ps -> ps.setLong(1, id),
            (rsItem, rowNum) -> {
                long produtoId = rsItem.getLong("produto_id");
                int quantidade = rsItem.getInt("quantidade");
                Produto produto = produtosRepository.recuperaProdutoPorid(produtoId);
                return new ItemPedido(produto, quantidade);
            }
        );

        // Recuperar cliente pelo CPF
        Cliente cliente = clienteRepository.recuperarPorCpf(clienteCpf);
        if (cliente == null) {
            // Fallback se cliente não encontrado
            cliente = new Cliente("N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
        }

        return new Pedido(id, cliente, itens, status, valor, impostos, desconto, valorCobrado, dataCriacao);
    }
}
