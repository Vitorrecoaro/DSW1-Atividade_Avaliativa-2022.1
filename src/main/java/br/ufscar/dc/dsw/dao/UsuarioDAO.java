package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

    public void insert(Usuario usuario) {

        String sql = "INSERT INTO USUARIO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, usuario.getId());
            statement.setString(2, usuario.getCPF());
            statement.setString(3, usuario.getLogin());
            statement.setString(4, usuario.getSenha());
            statement.setString(5, usuario.getEmail());
            statement.setString(6, usuario.getNome());
            statement.setString(7, usuario.getTelefone());
            statement.setString(8, String.valueOf(usuario.getSexo()));
            statement.setDate(9, usuario.getDataNasc());
            statement.setString(10, usuario.getTipo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {

        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * from USUARIO";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String nome = resultSet.getString("user_nome");
                String login = resultSet.getString("user_login");
                String senha = resultSet.getString("user_senha");
                String email = resultSet.getString("user_email");
                String CPF = resultSet.getString("user_CPF");
                String telefone = resultSet.getString("user_telefone");
                char sexo = resultSet.getString("user_sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("user_data_nasc");
                String tipo_de_usuario = resultSet.getString("user_tipo");
                Usuario usuario = new Usuario(id, nome, login,
                        senha, email, CPF, telefone, sexo, data_de_nasc,
                        tipo_de_usuario);
                listaUsuarios.add(usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public void delete(Usuario usuario) {
        String sql = "DELETE FROM USUARIO where user_CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getCPF());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE USUARIO SET user_nome = ?, user_login = ?, user_senha = ?, user_email = ?, user_CPF = ?, user_telefone = ?, user_sexo = ?, user_data_nasc = ?, user_tipo = ? WHERE user_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getLogin());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getCPF());
            statement.setString(6, usuario.getTelefone());
            statement.setString(7, String.valueOf(usuario.getSexo()));
            statement.setObject(8, usuario.getDataNasc());
            statement.setString(9, usuario.getTipo());
            statement.setLong(10, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario getbyID(Long id) {
        Usuario usuario = null;

        String sql = "SELECT * from USUARIO WHERE user_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("user_nome");
                String login = resultSet.getString("user_login");
                String senha = resultSet.getString("user_senha");
                String email = resultSet.getString("user_email");
                String CPF = resultSet.getString("user_CPF");
                String telefone = resultSet.getString("user_telefone");
                char sexo = resultSet.getString("user_sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("user_data_nasc");
                String tipo_de_usuario = resultSet.getString("user_tipo");

                usuario = new Usuario(id, nome, login, senha, email, CPF,
                        telefone, sexo, data_de_nasc, tipo_de_usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Usuario getbyLogin(String login) {
        Usuario usuario = null;

        String sql = "SELECT * from USUARIO WHERE user_login = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String nome = resultSet.getString("user_nome");
                String senha = resultSet.getString("user_senha");
                String email = resultSet.getString("user_email");
                String CPF = resultSet.getString("user_CPF");
                String telefone = resultSet.getString("user_telefone");
                char sexo = resultSet.getString("user_sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("user_data_nasc");
                String tipo_de_usuario = resultSet.getString("user_tipo");

                usuario = new Usuario(id, nome, login, senha, email, CPF,
                        telefone, sexo, data_de_nasc, tipo_de_usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}