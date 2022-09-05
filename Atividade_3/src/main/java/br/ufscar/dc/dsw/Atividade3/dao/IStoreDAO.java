package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Store;

public interface IStoreDAO extends JpaRepository<Store, Long> {

    Store findByEmail(String email);
}