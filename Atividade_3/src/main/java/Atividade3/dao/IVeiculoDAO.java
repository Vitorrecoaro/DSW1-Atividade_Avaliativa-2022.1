package Atividade3.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Atividade3.domain.Store;
import Atividade3.domain.Veiculo;

public interface IVeiculoDAO extends JpaRepository<Veiculo, Long> {
    public List<Veiculo> findAllByLoja(Store loja);

    public List<Veiculo> findAllByModelo(String modelo);
}