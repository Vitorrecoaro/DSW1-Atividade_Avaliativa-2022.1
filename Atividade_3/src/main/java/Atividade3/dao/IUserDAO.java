package Atividade3.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Atividade3.domain.User;

public interface IUserDAO extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}
