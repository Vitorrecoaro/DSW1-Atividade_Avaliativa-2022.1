package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Pessoa;
import br.ufscar.dc.dsw.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class PessoaDAO extends GenericDAO {
    public void insert(Pessoa pessoa) {

        String sql = "INSERT INTO `PESSOA FISICA`(pessoa_CPF, pessoa_email, pessoa_telefone, pessoa_sexo, pessoa_data_nasc, pessoa_tipo) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setString(1, pessoa.getCPF());
            statement.setString(2, pessoa.getUsuario().getEmail());
            statement.setString(3, pessoa.getTelefone());
            statement.setString(4, String.valueOf(pessoa.getSexo()));
            statement.setDate(5, pessoa.getDataNasc());
            statement.setString(6, pessoa.getTipo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pessoa> getAll() {

        List<Pessoa> listaPessoas = new ArrayList<>();

        String sql = "SELECT * from `PESSOA FISICA`";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                UsuarioDAO userDao = new UsuarioDAO();
                Long id = resultSet.getLong("pessoa_id");
                String CPF = resultSet.getString("pessoa_CPF");
                String email = resultSet.getString("pessoa_email");
                String telefone = resultSet.getString("pessoa_telefone");
                char sexo = resultSet.getString("pessoa_sexo").charAt(0);
                Date nasc = resultSet.getDate("pessoa_data_nasc");
                String tipo = resultSet.getString("pessoa_tipo");
                Usuario user = userDao.getbyEmail(email);
                Pessoa pessoa = new Pessoa(id, user, CPF, telefone,
                        sexo, nasc, tipo);
                listaPessoas.add(pessoa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPessoas;
    }

    public void delete(Pessoa pessoa) {
        String sql = "DELETE FROM `PESSOA FISICA` where pessoa_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pessoa.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Pessoa pessoa) {
        String sql = "UPDATE `PESSOA FISICA` SET pessoa_CPF = ?, pessoa_email = ?, pessoa_telefone = ?, pessoa_sexo = ?, pessoa_data_nasc = ?, pessoa_tipo = ? WHERE pessoa_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, pessoa.getCPF());
            statement.setString(2, pessoa.getUsuario().getEmail());
            statement.setString(3, pessoa.getTelefone());
            statement.setString(4, String.valueOf(pessoa.getSexo()));
            statement.setDate(5, pessoa.getDataNasc());
            statement.setString(6, pessoa.getTipo());
            statement.setLong(7, pessoa.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pessoa getbyID(Long id) {
        Pessoa pessoa = null;

        String sql = "SELECT * from `PESSOA FISICA` WHERE pessoa_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UsuarioDAO userDao = new UsuarioDAO();
                String CPF = resultSet.getString("pessoa_CPF");
                String email = resultSet.getString("pessoa_email");
                String telefone = resultSet.getString("pessoa_telefone");
                char sexo = resultSet.getString("pessoa_sexo").charAt(0);
                Date nasc = resultSet.getDate("pessoa_data_nasc");
                String tipo = resultSet.getString("pessoa_tipo");
                Usuario user = userDao.getbyEmail(email);
                pessoa = new Pessoa(id, user, CPF, telefone,
                        sexo, nasc, tipo);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pessoa;
    }

    public Pessoa getbyCPF(String cpf) {
        Pessoa pessoa = null;

        String sql = "SELECT * from `PESSOA FISICA` WHERE pessoa_CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UsuarioDAO userDao = new UsuarioDAO();
                Long id = resultSet.getLong("pessoa_id");
                String email = resultSet.getString("pessoa_email");
                String telefone = resultSet.getString("pessoa_telefone");
                char sexo = resultSet.getString("pessoa_sexo").charAt(0);
                Date nasc = resultSet.getDate("pessoa_data_nasc");
                String tipo = resultSet.getString("pessoa_tipo");
                Usuario user = userDao.getbyEmail(email);
                pessoa = new Pessoa(id, user, cpf, telefone,
                        sexo, nasc, tipo);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pessoa;
    }

    public Pessoa getbyEmail(String email) {
        Pessoa pessoa = null;

        String sql = "SELECT * from `PESSOA FISICA` WHERE pessoa_email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UsuarioDAO userDao = new UsuarioDAO();
                Long id = resultSet.getLong("pessoa_id");
                String CPF = resultSet.getString("pessoa_CPF");
                String telefone = resultSet.getString("pessoa_telefone");
                char sexo = resultSet.getString("pessoa_sexo").charAt(0);
                Date nasc = resultSet.getDate("pessoa_data_nasc");
                String tipo = resultSet.getString("pessoa_tipo");
                Usuario user = userDao.getbyEmail(email);
                pessoa = new Pessoa(id, user, CPF, telefone,
                        sexo, nasc, tipo);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pessoa;
    }
}
