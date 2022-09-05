package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "cliente")
public class Costumer extends User {

    @NotBlank
    @NotNull
    @Size(min = 11, max = 11)
    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @NotBlank
    @NotNull
    @Size(min = 11, max = 11)
    @Column(nullable = false, length = 11)
    private String telefone;

    @NotNull
    @Column
    private char sexo;

    @NotNull
    @Column(nullable = false)
    private Date dataNasc;

    @OneToMany(mappedBy = "usuario")
    private List<Proposta> listProposta;

    public Costumer() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void toUser(Costumer usuario) {
        this.dataNasc = usuario.dataNasc;
        this.sexo = usuario.sexo;
        this.telefone = usuario.telefone;
        this.cpf = usuario.cpf;
    }

    public List<Proposta> getListProposta() {
        return listProposta;
    }

    public void setListProposta(List<Proposta> listProposta) {
        this.listProposta = listProposta;
    }

}