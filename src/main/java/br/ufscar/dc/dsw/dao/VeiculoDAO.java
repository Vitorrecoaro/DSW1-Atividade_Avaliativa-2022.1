package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Veiculo;

public class VeiculoDAO extends GenericDAO {
    public void insert(Veiculo veiculo) {

        String sql = "INSERT INTO VEICULO(veic_placa, veic_loja_CNPJ, veic_modelo, veic_chassi, veic_ano, veic_quilometragem, veic_descricao, veic_valor, veic_fotos ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setString(1, veiculo.getPlaca());
            statement.setString(2, veiculo.getCNPJ());
            statement.setString(3, veiculo.getModelo());
            statement.setString(4, veiculo.getChassi());
            statement.setInt(5, veiculo.getAno());
            statement.setFloat(6, veiculo.getQuilometragem());
            statement.setString(7, veiculo.getDescricao());
            statement.setFloat(8, veiculo.getValor());
            statement.setString(9, veiculo.getFoto());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Veiculo> getAll() {

        List<Veiculo> listaVeiculos = new ArrayList<>();

        String sql = "SELECT * from VEICULO";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("veic_id");
                String placa = resultSet.getString("veic_placa");
                String CNPJ = resultSet.getString("veic_loja_CNPJ");
                String modelo = resultSet.getString("veic_modelo");
                String chassi = resultSet.getString("veic_chassi");
                int ano = resultSet.getInt("veic_ano");
                float kms = resultSet.getFloat("veic_quilometragem");
                String descricao = resultSet.getString("veic_descricao");
                float valor = resultSet.getFloat("veic_valor");
                String foto = resultSet.getString("veic_fotos");
                Veiculo veiculo = new Veiculo(id, placa, CNPJ,
                        modelo, chassi, ano, kms, descricao, valor,
                        foto);
                listaVeiculos.add(veiculo);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVeiculos;
    }

    public void delete(Veiculo veiculo) {
        String sql = "DELETE FROM VEICULO where veic_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, veiculo.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Veiculo veiculo) {
        String sql = "UPDATE VEICULO SET veic_placa = ?, veic_loja_CNPJ = ?, veic_modelo = ?, veic_chassi = ?, veic_ano = ?, veic_quilometragem = ?, veic_descricao = ?, veic_valor = ?, veic_fotos = ? WHERE veic_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, veiculo.getPlaca());
            statement.setString(2, veiculo.getCNPJ());
            statement.setString(3, veiculo.getModelo());
            statement.setString(4, veiculo.getChassi());
            statement.setLong(5, veiculo.getAno());
            statement.setFloat(6, veiculo.getQuilometragem());
            statement.setString(7, veiculo.getDescricao());
            statement.setFloat(8, veiculo.getValor());
            statement.setString(9, veiculo.getFoto());
            statement.setLong(10, veiculo.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Veiculo getbyID(Long id) {
        Veiculo veiculo = null;

        String sql = "SELECT * from VEICULO WHERE veic_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String placa = resultSet.getString("veic_placa");
                String CNPJ = resultSet.getString("veic_loja_CNPJ");
                String modelo = resultSet.getString("veic_modelo");
                String chassi = resultSet.getString("veic_chassi");
                int ano = resultSet.getInt("veic_ano");
                float kms = resultSet.getFloat("veic_quilometragem");
                String descricao = resultSet.getString("veic_descricao");
                float valor = resultSet.getFloat("veic_valor");
                String foto = resultSet.getString("veic_fotos");
                veiculo = new Veiculo(id, placa, CNPJ,
                        modelo, chassi, ano, kms, descricao, valor,
                        foto);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return veiculo;
    }

    public Veiculo getbyPlaca(String placa) {
        Veiculo veiculo = null;

        String sql = "SELECT * from VEICULO WHERE veic_placa = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, placa);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("veic_id");
                String CNPJ = resultSet.getString("veic_loja_CNPJ");
                String modelo = resultSet.getString("veic_modelo");
                String chassi = resultSet.getString("veic_chassi");
                int ano = resultSet.getInt("veic_ano");
                float kms = resultSet.getFloat("veic_quilometragem");
                String descricao = resultSet.getString("veic_descricao");
                float valor = resultSet.getFloat("veic_valor");
                String foto = resultSet.getString("veic_fotos");

                veiculo = new Veiculo(id, placa, CNPJ,
                        modelo, chassi, ano, kms, descricao, valor,
                        foto);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return veiculo;
    }
}
