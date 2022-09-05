package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.common;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StoreDTO {

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String senha;

    @NotBlank
    @NotNull
    @Size(max = 14, min = 14)
    @Column(unique = true, nullable = false, length = 14)
    private String cnpj;

    @Column(length = 255)
    private String descricao;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
