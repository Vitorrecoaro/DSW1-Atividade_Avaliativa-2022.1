package Atividade3.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Atividade3.domain.Costumer;
import Atividade3.domain.User;

public interface ICostumerDAO extends JpaRepository<Costumer, Long> {

    User findByEmail(String s);

}
