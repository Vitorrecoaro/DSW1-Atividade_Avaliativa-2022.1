package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Proposta;

public class PropostaDAO extends GenericDAO {
    public void insert(Proposta proposta) {

        String sql = "INSERT INTO PROPOSTA VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setLong(1, proposta.getId());
            statement.setString(2, proposta.getUserCPF());
            statement.setString(3, proposta.getVeicPlaca());
            statement.setDate(4, proposta.getData());
            statement.setFloat(5, proposta.getValor());
            statement.setString(6, proposta.getStatus());
            statement.setString(7, proposta.getLojaCNPJ());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Proposta> getAll() {

        List<Proposta> listaPropostas = new ArrayList<>();

        String sql = "SELECT * from PROPOSTA";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("prop_id");
                String CPF = resultSet.getString("prop_user_CPF");
                String placa = resultSet.getString("prop_veic_placa");
                Date data = resultSet.getDate("prop_data");
                float valor = resultSet.getFloat("prop_valor");
                String status = resultSet.getString("prop_status");
                String CNPJ = resultSet.getString("prop_loja_CNPJ");
                Proposta proposta = new Proposta(id, CPF, placa,
                        data, valor, status, CNPJ);
                listaPropostas.add(proposta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPropostas;
    }

    public void delete(Proposta proposta) {
        String sql = "DELETE FROM PROPOSTA where prop_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, proposta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Proposta proposta) {
        String sql = "UPDATE PROPOSTA SET prop_user_CPF = ?, prop_veic_placa = ?, prop_data = ?, prop_valor = ?, prop_status = ?, prop_loja_CNPJ = ? WHERE prop_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, proposta.getUserCPF());
            statement.setString(2, proposta.getVeicPlaca());
            statement.setDate(3, proposta.getData());
            statement.setFloat(4, proposta.getValor());
            statement.setString(5, proposta.getStatus());
            statement.setString(6, proposta.getLojaCNPJ());
            statement.setLong(7, proposta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Proposta getbyID(Long id) {
        Proposta proposta = null;

        String sql = "SELECT * from PROPOSTA WHERE prop_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String CPF = resultSet.getString("prop_user_CPF");
                String placa = resultSet.getString("prop_veic_placa");
                Date data = resultSet.getDate("prop_data");
                float valor = resultSet.getFloat("prop_valor");
                String status = resultSet.getString("prop_status");
                String CNPJ = resultSet.getString("prop_loja_CNPJ");
                proposta = new Proposta(id, CPF, placa,
                        data, valor, status, CNPJ);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return proposta;
    }

    public Proposta getbyPlaca(String placa) {
        Proposta proposta = null;

        String sql = "SELECT * from PROPOSTA WHERE prop_veic_placa = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, placa);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("prop_id");
                String CPF = resultSet.getString("prop_user_CPF");
                Date data = resultSet.getDate("prop_data");
                float valor = resultSet.getFloat("prop_valor");
                String status = resultSet.getString("prop_status");
                String CNPJ = resultSet.getString("prop_loja_CNPJ");

                proposta = new Proposta(id, CPF, placa,
                        data, valor, status, CNPJ);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return proposta;
    }
}
