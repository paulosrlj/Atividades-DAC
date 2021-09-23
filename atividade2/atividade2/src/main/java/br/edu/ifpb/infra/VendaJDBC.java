package br.edu.ifpb.infra;

import br.edu.ifpb.domain.produto.Produto;
import br.edu.ifpb.domain.venda.*;
import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class VendaJDBC implements Vendas {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;

    private Connection conn;

    @Override
    public Venda novaVenda(Venda venda) {
        try {
            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement(
                            "INSERT INTO venda (cliente_id) VALUES(?) RETURNING *; "
                    );

            statement.setInt(1, venda.getCliente_id());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return criarVenda(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
        return new Venda();
    }

    @Override
    public void adicionarProduto(Produto_venda produto_venda) {
        try {
            this.conn = this.dataSource.getConnection();

            System.out.println("JDBC");
            System.out.println(produto_venda.getVenda_id());

            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "INSERT INTO produto_venda (venda_id, produto_id) VALUES(?, ?) RETURNING *; "
                    );

            statement.setInt(1, produto_venda.getVenda_id());
            statement.setInt(2, produto_venda.getProduto_id());

            statement.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(VendaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    @Override
    public List<Venda> listarVendas() {
        List<Venda> lista = new ArrayList<>();
        try {
            this.conn = this.dataSource.getConnection();

            ResultSet result = this.conn
                    .prepareStatement(
                            "SELECT * FROM venda"
                    ).executeQuery();

            while (result.next()) {
                lista.add(
                        criarVenda(result)
                );
            }

        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
        return lista;
    }

    private Venda criarVenda(ResultSet result) throws SQLException {
        int cliente_id = result.getInt("cliente_id");
        int id = result.getInt("id");
        return new Venda(id, cliente_id);
    }

    private Produto criarProduto(ResultSet result) throws SQLException {
        String descricao = result.getString("descricao");
        Double valor = result.getDouble("valor");
        int id = result.getInt("id");
        return new Produto(id, descricao, new BigDecimal(valor));
    }

    @Override
    public Venda localizarVenda(int id) {
        try {
            this.conn = this.dataSource.getConnection();

            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "SELECT * FROM venda WHERE id = ?"
                    );
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                if (conn != null) {
                    conn.close();
                }
                return criarVenda(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
        return new Venda();
    }

    @Override
    public List<Produto> listarProdutosDeVenda(int id) {
        List<Produto> lista = new ArrayList<>();

        try {
            this.conn = this.dataSource.getConnection();

            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "SELECT P.id, P.descricao, P.valor "
                            + "FROM produto_venda PV INNER JOIN produtos P "
                            + "ON PV.produto_id = P.id "
                            + "WHERE PV.venda_id = ?;"
                    );

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                lista.add(
                        criarProduto(result)
                );
            }

        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
        return lista;
    }

    @Override
    public void deletarVenda(int id) {
        try {
            Venda venda = this.localizarVenda(id);

            if (venda == null) {
                return;
            }

            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "DELETE FROM produto_venda WHERE venda_id = ?"
                    );
            statement.setInt(1, venda.getId());
            statement.executeUpdate();

            statement = this.conn
                    .prepareStatement(
                            "DELETE FROM venda WHERE id = ?"
                    );

            statement.setInt(1, venda.getId());
            statement.executeUpdate();
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(VendaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
    }
}
