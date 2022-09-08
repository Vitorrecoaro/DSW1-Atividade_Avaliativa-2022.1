package Atividade3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import Atividade3.dao.IUserDAO;
import Atividade3.domain.User;

@Repository
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private IUserDAO dao;

    @Override
    public UserDetails loadUserByUsername(String s)
            throws UsernameNotFoundException {
        User user = dao.findByEmail(s);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new UsuarioDetails(user);
    }
}
