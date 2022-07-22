package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Proposta {
    private int id;
    private String user_cpf;
    private String veic_placa;
    private Date data;
    private float valor;
    private String status;
    private String CNPJ_loja_responsavel;

    public Proposta(int id, String CPF, String veic_placa, Date data,
            float valor, String status, String loja) {
        this.id = id;
        this.user_cpf = CPF;
        this.veic_placa = veic_placa;
        this.data = data;
        this.valor = valor;
        this.status = status;
        this.CNPJ_loja_responsavel = loja;
    }

    public int getId() {
        return id;
    }

    public String getUserCPF() {
        return user_cpf;
    }

    public String getVeicPlaca() {
        return veic_placa;
    }

    public Date getData() {
        return data;
    }

    public float getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public String getLojaCNPJ() {
        return CNPJ_loja_responsavel;
    }
}