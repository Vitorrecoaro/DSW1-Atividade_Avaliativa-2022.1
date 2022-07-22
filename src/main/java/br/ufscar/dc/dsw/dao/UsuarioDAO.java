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

        String sql = "INSERT INTO Usuario (user_id, nome, login, senha, email, CPF, telefone, sexo, data_de_nasc, tipo_de_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setInt(1, usuario.getId());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getLogin());
            statement.setString(4, usuario.getSenha());
            statement.setString(5, usuario.getEmail());
            statement.setString(6, usuario.getCPF());
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

        String sql = "SELECT * from Usuario u";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                String CPF = resultSet.getString("CPF");
                String telefone = resultSet.getString("telefone");
                char sexo = resultSet.getString("sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("data_de_nasc");
                String tipo_de_usuario = resultSet.getString("tipo_de_usuario");
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
        String sql = "DELETE FROM Usuario where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, login = ?, senha = ?, email = ?, CPF = ?, telefone = ?, sexo = ?, datanasc = ?, tipo = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            // statement.setString(1, usuario.getNome());
            // statement.setString(2, usuario.getLogin());
            // statement.setString(3, usuario.getSenha());
            // statement.setString(4, usuario.getEmail());
            // statement.executeUpdate();

            statement.setInt(1, usuario.getId());
            statement.setString(2, usuario.getNome());
            statement.setString(3, usuario.getLogin());
            statement.setString(4, usuario.getSenha());
            statement.setString(5, usuario.getEmail());
            statement.setString(6, usuario.getCPF());
            statement.setString(7, usuario.getTelefone());
            statement.setString(8, String.valueOf(usuario.getSexo()));
            statement.setObject(9, usuario.getDataNasc());
            statement.setString(10, usuario.getTipo());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario getbyID(int id) {
        Usuario usuario = null;

        String sql = "SELECT * from Usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // long id = resultSet.getLong("id");
                // String nome = resultSet.getString("nome");
                // String login = resultSet.getString("login");
                // String senha = resultSet.getString("senha");
                // String papel = resultSet.getString("papel");

                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                String CPF = resultSet.getString("CPF");
                String telefone = resultSet.getString("telefone");
                char sexo = resultSet.getString("sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("data_de_nasc");
                String tipo_de_usuario = resultSet.getString("tipo_de_usuario");

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

        String sql = "SELECT * from Usuario WHERE login = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // long id = resultSet.getLong("id");
                // String nome = resultSet.getString("nome");
                // String login = resultSet.getString("login");
                // String senha = resultSet.getString("senha");
                // String papel = resultSet.getString("papel");

                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                String CPF = resultSet.getString("CPF");
                String telefone = resultSet.getString("telefone");
                char sexo = resultSet.getString("sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("data_de_nasc");
                String tipo_de_usuario = resultSet.getString("tipo_de_usuario");

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