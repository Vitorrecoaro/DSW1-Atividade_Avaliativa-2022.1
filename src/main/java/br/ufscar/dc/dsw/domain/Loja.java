package br.ufscar.dc.dsw.domain;

import java.math.BigInteger;

public class Loja {

    private Long id;
    private String CNPJ;
    private String email;
    private String senha;
    private String nome;
    private String descricao;

    public Loja(Long id, String CNPJ, String email, String senha,
            String nome, String descricao) {
        if (CNPJ.length() == 14) {
            this.id = id;
            this.CNPJ = CNPJ;
            this.email = email;
            this.senha = senha;
            this.nome = nome;
            this.descricao = descricao;
        }
    }

    public Long getId() {
        return id;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    // public void setCNPJ(String CNPJ) {
    // if (CNPJ.length() == 14)
    // this.CNPJ = CNPJ;
    // }

    public String getEmail() {
        return email;
    }

    // public void setEmail(String email) {
    // this.email = email;
    // }

    public String getSenha() {
        return senha;
    }

    // public void setSenha(String senha) {
    // this.senha = senha;
    // }

    public String getNome() {
        return nome;
    }

    // public void setNome(String nome) {
    // this.nome = nome;
    // }

    public String getDescricao() {
        return descricao;
    }

    // public void setDescricao(String descricao) {
    // this.descricao = descricao;
    // }
}
