package Atividade3.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Atividade3.domain.Store;

public interface IStoreDAO extends JpaRepository<Atividade3.domain.Store, Long> {

    Store findByEmail(String email);
}