package br.edu.ifpb.infra;

import br.edu.ifpb.domain.produto.*;
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
public class ProdutosEmJDBC implements Produtos {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;
    private Connection conn;

    @Override
    public Produto novo(Produto produto) {
        try {
            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "INSERT INTO produtos (descricao, valor) VALUES(?,?) RETURNING *; "
                    );
            statement.setString(1, produto.getDescricao());
            statement.setDouble(2, produto.getValor().doubleValue());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                if (conn != null) {
                    conn.close();
                }
                return criarProduto(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
        return new Produto();
    }

    @Override
    public List<Produto> todos() {
        List<Produto> lista = new ArrayList<>();

        try {
            this.conn = this.dataSource.getConnection();
            lista = new ArrayList<>();
            ResultSet result = this.conn
                    .prepareStatement(
                            "SELECT * FROM produtos"
                    ).executeQuery();

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

    private Produto criarProduto(ResultSet result) throws SQLException {
        String descricao = result.getString("descricao");
        Double valor = result.getDouble("valor");
        int id = result.getInt("id");
        return new Produto(id, descricao, new BigDecimal(valor));
    }

    @Override
    public Produto localizar(int id) {
        try {
            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "SELECT * FROM produtos WHERE id = ?"
                    );
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                if (conn != null) {
                    conn.close();
                }
                return criarProduto(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
        return new Produto();
    }

    @Override
    public Produto localizarPorDescricao(String descricao) {
        try {
            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "SELECT * FROM produtos WHERE descricao = ?"
                    );
            statement.setString(1, descricao);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                if (conn != null) {
                    conn.close();
                }
                return criarProduto(result);
            }
        } catch (SQLException ex) {
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
        return new Produto();
    }

    @Override
    public void atualizar(Produto produto, int id) {
        try {
            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "UPDATE produtos "
                            + "SET id = ?, descricao = ?, valor = ? "
                            + "WHERE id = ?"
                    );

            statement.setInt(1, id);
            statement.setString(2, produto.getDescricao());
            statement.setDouble(3, produto.getValor().doubleValue());
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
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
    public void deletar(int id) {
        try {
            Produto produto = this.localizar(id);

            if (produto == null) {
                return;
            }
            
            this.conn = this.dataSource.getConnection();
            
            PreparedStatement statement = this.conn.prepareStatement(
                    "DELETE FROM produtos WHERE id = ?"
            );

            statement.setInt(1, produto.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
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
