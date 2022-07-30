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

        String sql = "INSERT INTO USUARIO(user_CPF, user_senha, user_email, user_nome, user_telefone, user_sexo, user_data_nasc, user_tipo) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getCPF());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getNome());
            statement.setString(5, usuario.getTelefone());
            statement.setString(6, String.valueOf(usuario.getSexo()));
            statement.setDate(7, usuario.getDataNasc());
            statement.setString(8, usuario.getTipo());
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
                String senha = resultSet.getString("user_senha");
                String email = resultSet.getString("user_email");
                String CPF = resultSet.getString("user_CPF");
                String telefone = resultSet.getString("user_telefone");
                char sexo = resultSet.getString("user_sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("user_data_nasc");
                String tipo_de_usuario = resultSet.getString("user_tipo");
                Usuario usuario = new Usuario(id, nome,
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
        String sql = "DELETE FROM USUARIO where user_id = ?";

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
        String sql = "UPDATE USUARIO SET user_nome = ?, user_senha = ?, user_email = ?, user_CPF = ?, user_telefone = ?, user_sexo = ?, user_data_nasc = ?, user_tipo = ? WHERE user_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getCPF());
            statement.setString(5, usuario.getTelefone());
            statement.setString(6, String.valueOf(usuario.getSexo()));
            statement.setDate(7, usuario.getDataNasc());
            statement.setString(8, usuario.getTipo());
            statement.setLong(9, usuario.getId());
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
                String senha = resultSet.getString("user_senha");
                String email = resultSet.getString("user_email");
                String CPF = resultSet.getString("user_CPF");
                String telefone = resultSet.getString("user_telefone");
                char sexo = resultSet.getString("user_sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("user_data_nasc");
                String tipo_de_usuario = resultSet.getString("user_tipo");

                usuario = new Usuario(id, nome, senha, email, CPF,
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

    public Usuario getbyCPF(String cpf) {
        Usuario usuario = null;

        String sql = "SELECT * from USUARIO WHERE user_CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String nome = resultSet.getString("user_nome");
                String senha = resultSet.getString("user_senha");
                String email = resultSet.getString("user_email");
                String telefone = resultSet.getString("user_telefone");
                char sexo = resultSet.getString("user_sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("user_data_nasc");
                String tipo_de_usuario = resultSet.getString("user_tipo");

                usuario = new Usuario(id, nome, senha, email, cpf,
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

    public Usuario getbyEmail(String email) {
        Usuario usuario = null;

        String sql = "SELECT * from USUARIO WHERE user_email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String nome = resultSet.getString("user_nome");
                String senha = resultSet.getString("user_senha");
                String cpf = resultSet.getString("user_CPF");
                String telefone = resultSet.getString("user_telefone");
                char sexo = resultSet.getString("user_sexo").charAt(0);
                Date data_de_nasc = resultSet.getDate("user_data_nasc");
                String tipo_de_usuario = resultSet.getString("user_tipo");

                usuario = new Usuario(id, nome, senha, email, cpf,
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