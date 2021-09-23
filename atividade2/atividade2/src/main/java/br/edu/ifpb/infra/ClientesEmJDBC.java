package br.edu.ifpb.infra;

import br.edu.ifpb.domain.cliente.Clientes;
import br.edu.ifpb.domain.cliente.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class ClientesEmJDBC implements Clientes {

    @Resource(lookup = "java:app/jdbc/pgadmin")
    private DataSource dataSource;
    private Connection conn;

    @Override
    public Cliente novo(Cliente cliente) {
        try {
            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "INSERT INTO clientes (cpf, nome) VALUES(?,?) RETURNING *; "
                    );
            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getNome());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                if (conn != null) {
                    conn.close();
                }
                return criarCliente(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }

        return new Cliente();
    }

    @Override
    public List<Cliente> todos() {
        List<Cliente> lista = new ArrayList<>();
        try {
            this.conn = this.dataSource.getConnection();

            lista = new ArrayList<>();
            ResultSet result = this.conn.prepareStatement(
                    "SELECT * FROM clientes"
            ).executeQuery();

            while (result.next()) {
                lista.add(
                        criarCliente(result)
                );
            }
        } catch (SQLException ex) {
//            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
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

    private Cliente criarCliente(ResultSet result) throws SQLException {
        String nome = result.getString("nome");
        String cpf = result.getString("cpf");
        int id = result.getInt("id");
        return new Cliente(id, cpf, nome);
    }

    @Override
    public Cliente localizar(int id) {
        try {
            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "SELECT * FROM clientes WHERE id = ?"
                    );
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                if (conn != null) {
                    conn.close();
                }
                return criarCliente(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

            }
        }
        return new Cliente();
    }

    @Override
    public void atualizar(Cliente cliente, int id) {
        try {
            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "UPDATE clientes "
                            + "SET id = ?, nome = ?, cpf = ? "
                            + "WHERE id = ?"
                    );

            statement.setInt(1, id);
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getCpf());
            statement.setInt(4, id);

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
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
            Cliente cliente = this.localizar(id);

            if (cliente == null) {
                return;
            }
            
            this.conn = this.dataSource.getConnection();
            PreparedStatement statement = this.conn
                    .prepareStatement(
                            "DELETE FROM clientes WHERE id = ?"
                    );

            statement.setInt(1, cliente.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
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
