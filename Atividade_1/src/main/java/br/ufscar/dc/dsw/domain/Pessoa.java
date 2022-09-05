package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Pessoa {
    private Long id;
    private Usuario usuario;
    private String CPF;
    private String telefone;
    private char sexo;
    private Date data_de_nasc;
    private String tipo_de_usuario;

    public Pessoa(
            Usuario user, String CPF, String tel,
            char sexo, Date nasc, String tipo) {
        this.usuario = user;
        this.CPF = CPF;
        this.telefone = tel;
        this.sexo = sexo;
        this.data_de_nasc = nasc;
        this.tipo_de_usuario = tipo;
    }

    public Pessoa(
            Long id, Usuario user, String CPF, String tel,
            char sexo, Date nasc, String tipo) {
        this.id = id;
        this.usuario = user;
        this.CPF = CPF;
        this.telefone = tel;
        this.sexo = sexo;
        this.data_de_nasc = nasc;
        this.tipo_de_usuario = tipo;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setCPF(String CPF) {
        if (CPF.length() == 11)
            this.CPF = CPF;
    }

    public String getCPF() {
        return this.CPF;
    }

    public void setTelefone(String telefone) {
        if (telefone.length() == 12)
            this.telefone = telefone;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setSexo(char sexo) {
        if (sexo == 'M' || sexo == 'F')
            this.sexo = sexo;
    }

    public char getSexo() {
        return this.sexo;
    }

    public void setDataNasc(Date data) {
        if (Date.valueOf(LocalDate.now()).getYear() - data.getYear() < 100)
            this.data_de_nasc = data;
    }

    public Date getDataNasc() {
        return this.data_de_nasc;
    }

    public void setTipo(String tipo) {
        if (tipo.length() == 10)
            this.tipo_de_usuario = tipo;
    }

    public String getTipo() {
        return this.tipo_de_usuario;
    }
}
