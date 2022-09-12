package Atividade3;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Atividade3.dao.ICostumerDAO;
import Atividade3.dao.IPropostaDAO;
import Atividade3.dao.IStoreDAO;
import Atividade3.dao.IUserDAO;
import Atividade3.dao.IVeiculoDAO;
import Atividade3.domain.Costumer;
import Atividade3.domain.Proposta;
import Atividade3.domain.Store;
import Atividade3.domain.Veiculo;

@SpringBootApplication
public class DswAtividade3AplicacaoRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DswAtividade3AplicacaoRestApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IUserDAO userDAO, IStoreDAO storeDAO, ICostumerDAO costumerDAO,
            BCryptPasswordEncoder encoder, IVeiculoDAO veiculoDAO, IPropostaDAO propostaDAO) {
        return (args) -> {
            Costumer admin = new Costumer();
            admin.setCpf("20193834932");
            admin.setDataNasc(Date.valueOf("2000-03-24"));
            admin.setEmail("admin@admin.com");
            admin.setNome("Ademira");
            admin.setSenha(encoder.encode("admin"));
            admin.setSexo('F');
            admin.setTelefone("11970707070");
            admin.setTipo("ROLE_ADMIN");
            costumerDAO.save(admin);

            Store loja = new Store();
            loja.setCnpj("01234567019234");
            loja.setDescricao("Preço barato é aqui");
            loja.setEmail("loja@loja.com");
            loja.setNome("Loja, a loja");
            loja.setSenha(encoder.encode("loja"));
            loja.setTipo("ROLE_STORE");
            storeDAO.save(loja);

            Store loja2 = new Store();
            loja2.setCnpj("09876543212345");
            loja2.setDescricao("Temos preços para qualquer público");
            loja2.setEmail("loja2@loja.com");
            loja2.setNome("Star Veiculos");
            loja2.setSenha(encoder.encode("loja"));
            loja2.setTipo("ROLE_STORE");
            storeDAO.save(loja2);

            Costumer user = new Costumer();
            user.setCpf("12357242461");
            user.setDataNasc(Date.valueOf("2000-05-02"));
            user.setEmail("user@user.com");
            user.setNome("Wando");
            user.setSenha(encoder.encode("user"));
            user.setSexo('M');
            user.setTelefone("11970707071");
            user.setTipo("ROLE_USER");
            costumerDAO.save(user);

            Veiculo veic = new Veiculo();
            veic.setPlaca("ABC12345");
            veic.setQuilometragem(200);
            veic.setValor(20000);
            veic.setAno(2002);
            veic.setChassi("FGTW2022");
            veic.setDescricao("O carro do ano");
            veic.setLoja(loja);
            veic.setModelo("TiggoX5");
            veiculoDAO.save(veic);

            Veiculo veic2 = new Veiculo();
            veic2.setPlaca("CCC10290");
            veic2.setQuilometragem(30000);
            veic2.setValor(8000);
            veic2.setAno(1997);
            veic2.setChassi("ESC1097");
            veic2.setDescricao("É velho mais dá para o gasto.");
            veic2.setLoja(loja);
            veic2.setModelo("Escort");
            veiculoDAO.save(veic2);

            Proposta prop = new Proposta();
            prop.setData_proposta(Date.valueOf(LocalDate.now()));
            prop.setModo_de_pagamento("A vista");
            prop.setPreco((long) 20000);
            prop.setStatus("ABERTO");
            prop.setUsuario(user);
            prop.setVeiculo(veic);
            propostaDAO.save(prop);

            Proposta prop2 = new Proposta();
            prop2.setData_proposta(Date.valueOf(LocalDate.now()));
            prop2.setModo_de_pagamento("A vista");
            prop2.setPreco((long) 38000);
            prop2.setStatus("ABERTO");
            prop2.setUsuario(user);
            prop2.setVeiculo(veic2);
            propostaDAO.save(prop2);
        };

    }

}
