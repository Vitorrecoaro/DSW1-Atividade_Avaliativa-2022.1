package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Store;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Veiculo;

public interface IVeiculoDAO extends JpaRepository<Veiculo, Long> {
    public List<Veiculo> findAllByLoja(Store loja);
}