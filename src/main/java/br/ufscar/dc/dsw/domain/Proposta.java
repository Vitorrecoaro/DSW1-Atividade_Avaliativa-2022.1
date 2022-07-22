package br.ufscar.dc.dsw.domain;

import java.math.BigInteger;
import java.time.LocalDate;

public class Proposta {
    private static int id;
    private Usuario user;
    private Veiculo veic;
    private LocalDate data;
    private String valor;
    private String status;
    private Loja loja_responsavel;

    public Proposta(Usuario user, Veiculo veic, LocalDate data,
            String valor, String status, Loja loja) {
        this.user = user;
        this.veic = veic;
        this.data = data;
        this.valor = valor;
        this.status = status;
        this.loja_responsavel = loja;
    }

    public int getId() {
        return id;
    }

    public Usuario getUser() {
        return user;
    }

    public Veiculo getVeic() {
        return veic;
    }

    public LocalDate getData() {
        return data;
    }

    public String getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public Loja getLoja() {
        return loja_responsavel;
    }
}