package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IUserDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.User;

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
