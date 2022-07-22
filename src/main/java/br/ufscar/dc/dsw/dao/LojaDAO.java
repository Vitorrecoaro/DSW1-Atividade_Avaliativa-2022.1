package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Loja;

public class LojaDAO extends GenericDAO {

    public void insert(Loja loja) {

        String sql = "INSERT INTO LOJA VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setInt(1, loja.getId());
            statement.setString(2, loja.getCNPJ());
            statement.setString(3, loja.getEmail());
            statement.setString(4, loja.getSenha());
            statement.setString(5, loja.getNome());
            statement.setString(6, loja.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Loja> getAll() {

        List<Loja> listaLojas = new ArrayList<>();

        String sql = "SELECT * FROM LOJA u";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("loja_id");
                String CNPJ = resultSet.getString("loja_CNPJ");
                String email = resultSet.getString("loja_email");
                String senha = resultSet.getString("loja_senha");
                String nome = resultSet.getString("loja_nome");
                String descricao = resultSet.getString("loja_descricao");
                Loja loja = new Loja(id, CNPJ,
                        email, senha, nome, descricao);
                listaLojas.add(loja);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLojas;
    }

    public void delete(Loja loja) {
        String sql = "DELETE FROM LOJA where loja_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, loja.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Loja loja) {
        String sql = "UPDATE LOJA SET loja_CNPJ = ?, loja_email = ?, loja_senha = ?, loja_nome = ?, loja_descricao = ? WHERE loja_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, loja.getCNPJ());
            statement.setString(2, loja.getEmail());
            statement.setString(3, loja.getSenha());
            statement.setString(4, loja.getNome());
            statement.setString(5, loja.getDescricao());
            statement.setInt(6, loja.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Loja getbyID(int id) {
        Loja loja = null;

        String sql = "SELECT * from LOJA WHERE loja_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String CNPJ = resultSet.getString("loja_CNPJ");
                String email = resultSet.getString("loja_email");
                String senha = resultSet.getString("loja_senha");
                String nome = resultSet.getString("loja_nome");
                String descricao = resultSet.getString("loja_descricao");

                loja = new Loja(id, CNPJ, email, senha, nome, descricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loja;
    }

    public Loja getbyEmail(String email) {
        Loja loja = null;

        String sql = "SELECT * from LOJA WHERE loja_email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("loja_id");
                String CNPJ = resultSet.getString("loja_CNPJ");
                String senha = resultSet.getString("loja_senha");
                String nome = resultSet.getString("loja_nome");
                String descricao = resultSet.getString("loja_descricao");

                loja = new Loja(id, CNPJ, email, senha, nome, descricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loja;
    }
}