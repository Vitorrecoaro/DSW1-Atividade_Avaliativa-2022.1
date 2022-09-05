package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Proposta;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.User;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Veiculo;

public interface IPropostaDAO extends JpaRepository<Proposta, Long> {
    public List<Proposta> findAllByUsuario(User user);

    public List<Proposta> findAllByVeiculo(Veiculo veiculo);
}
