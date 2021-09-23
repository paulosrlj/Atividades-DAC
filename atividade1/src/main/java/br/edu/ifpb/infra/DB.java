/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paulo
 */
public class DB {

    private Connection conn = null;

    public Connection getConnection() throws ClassNotFoundException {
        if (this.conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
                this.conn = DriverManager.getConnection(
                        "jdbc:postgresql://host-banco:5432/atividade1-dac",
                        "paulo", "123"
                );

            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }

        }
        return this.conn;
    }

    public void closeConnection() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    public List<Integrante> todos() throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            ArrayList<Integrante> lista = new ArrayList<>();
            ResultSet result = conn.prepareStatement(
                    "SELECT * FROM integrante"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                        criarIntegrante(result)
                );
            }
//            this.closeConnection();
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    public Integrante buscarPorId(int id) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM integrante WHERE id = ?"
            );

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            Integrante integrante = criarIntegrante(result);

            return integrante;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public Integrante criarIntegrante(ResultSet result) throws SQLException {
        String nome = result.getString("nome");
        String cpfNum = result.getString("CPF");
        int id = result.getInt("id");
        CPF cpf = new CPF(cpfNum);

        LocalDate dataNasc = result.getDate("dataDeNascimento").toLocalDate();
        return new Integrante(id, nome, dataNasc, cpf);
    }

    public void novo(Integrante integrante) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO integrante (nome, dataDeNascimento, CPF) VALUES(?,?::date,?)"
            );
            statement.setString(1, integrante.getNome());
            statement.setDate(2, Date.valueOf(integrante.getDataNascimento()));
            statement.setString(3, integrante.getCpf().getNumero());

            statement.executeUpdate();

//            this.closeConnection();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public void excluir(Integrante integrante) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM integrante_banda WHERE id_integrante = ?"
            );

            statement.setInt(1, integrante.getId());
            statement.executeUpdate();

            statement = conn.prepareStatement(
                    "DELETE FROM integrante WHERE id = ?"
            );
            statement.setInt(1, integrante.getId());

            statement.executeUpdate();

//            this.closeConnection();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public void editar(Integrante integrante) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE integrante "
                    + "SET id = ?, nome = ?, dataDeNascimento = ?, CPF = ? "
                    + "WHERE id = ?"
            );

            statement.setInt(1, integrante.getId());
            statement.setString(2, integrante.getNome());
            statement.setDate(3, Date.valueOf(integrante.getDataNascimento()));
            statement.setString(4, integrante.getCpf().getNumero());
            statement.setInt(5, integrante.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public Integrante buscarPorCpf(String cpf) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM integrante "
                    + "WHERE cpf = ?"
            );

            statement.setString(1, cpf);

            ResultSet result = statement.executeQuery();
            result.next();
            Integrante integrante = criarIntegrante(result);

            return integrante;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    /**
     *
     * Banda
     *
     */
    public Banda criarBanda(ResultSet result) throws SQLException {
        String localdeorigem = result.getString("localdeorigem");
        String nomefantasia = result.getString("nomefantasia");
        int id = result.getInt("id");

        return new Banda(id, localdeorigem, nomefantasia);
    }

    public void novaBanda(Banda banda) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO banda (localdeorigem, nomefantasia) VALUES(?,?)"
            );
            statement.setString(1, banda.getLocalDeOrigem());
            statement.setString(2, banda.getNomeFantasia());

            statement.executeUpdate();

//            this.closeConnection();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public List<Banda> listarTodasBandas() throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            ArrayList<Banda> lista = new ArrayList<>();
            ResultSet result = conn.prepareStatement(
                    "SELECT * FROM banda"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                        criarBanda(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    public Banda buscarBandaPorId(int id) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM banda WHERE id = ?"
            );

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            Banda banda = criarBanda(result);

            return banda;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public void editarBanda(Banda banda, Integrante integrante) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE banda "
                    + "SET id = ?, localdeorigem = ?, nomefantasia = ? "
                    + "WHERE id = ?"
            );

            statement.setInt(1, banda.getId());
            statement.setString(2, banda.getLocalDeOrigem());
            statement.setString(3, banda.getNomeFantasia());
            statement.setInt(4, banda.getId());

            statement.executeUpdate();

            if (integrante != null) {
                statement = conn.prepareStatement(
                        "INSERT INTO integrante_banda (id_banda, id_integrante) VALUES(?, ?)"
                );
                statement.setInt(1, banda.getId());
                statement.setInt(2, integrante.getId());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public void excluirBanda(Banda banda) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM integrante_banda WHERE id_banda = ?"
            );

            statement.setInt(1, banda.getId());
            statement.executeUpdate();

            statement = conn.prepareStatement(
                    "DELETE FROM banda WHERE id = ?"
            );
            statement.setInt(1, banda.getId());

            statement.executeUpdate();

//            this.closeConnection();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public List<Banda> buscarPorLocaldeorigem(String localdeorigem) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();
            ArrayList<Banda> lista = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * FROM banda "
                    + "WHERE localdeorigem = ?"
            );

            statement.setString(1, localdeorigem);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                lista.add(
                        criarBanda(result)
                );
            }
            return lista;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public List<Integrante> buscarIntegrantesBanda(int id) throws ClassNotFoundException {
        try {
            Connection conn = this.getConnection();
            ArrayList<Integrante> lista = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement(
                    "SELECT I.id, I.nome, I.datadenascimento, I.cpf "
                    + "FROM integrante I JOIN integrante_banda IB ON I.id = IB.id_integrante "
                    + "WHERE IB.id_banda = ?"
            );

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                lista.add(
                        criarIntegrante(result)
                );
            }
            return lista;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
