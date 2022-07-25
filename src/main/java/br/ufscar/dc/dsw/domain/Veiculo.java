package br.ufscar.dc.dsw.domain;

public class Veiculo {

    private Long id;
    private String veic_placa;
    private String veic_loja_CNPJ;
    private String veic_modelo;
    private String veic_chassi;
    private int veic_ano;
    private float veic_quilometragem;
    private String veic_descricao;
    private float veic_valor;
    private String veic_fotos;

    public Veiculo(
            Long id, String placa, String CNPJ, String modelo,
            String chassi, int ano, float quilometragem,
            String descricao, float valor, String fotos) {

        if (placa.length() == 7 && CNPJ.length() == 14) {
            this.veic_placa = placa;
            this.veic_loja_CNPJ = CNPJ;
            this.veic_modelo = modelo;
            this.veic_chassi = chassi;
            this.veic_ano = ano;
            this.veic_quilometragem = quilometragem;
            this.veic_descricao = descricao;
            this.veic_valor = valor;
            this.veic_fotos = fotos;
        }
    }

    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return veic_placa;
    }

    // public void setPlaca(String placa) {
    // if (placa.length() == 7)
    // this.veic_placa = placa;
    // }

    public String getCNPJ() {
        return veic_loja_CNPJ;
    }

    // public void setCNPJ(String cnpj) {
    // if (cnpj.length() == 14)
    // this.veic_loja_CNPJ = cnpj;
    // }

    public String getModelo() {
        return veic_modelo;
    }

    // public void setModelo(String modelo) {
    // this.veic_modelo = modelo;
    // }

    // public void setChassi(String chassi) {
    // this.veic_chassi = chassi;
    // }

    public String getChassi() {
        return this.veic_chassi;
    }

    // public void setAno(int ano) {
    // this.veic_ano = ano;
    // }

    public int getAno() {
        return veic_ano;
    }

    public void setQuilometragem(float quilometragem) {
        this.veic_quilometragem = quilometragem;
    }

    public float getQuilometragem() {
        return this.veic_quilometragem;
    }

    // public void setDescricao(String desc) {
    // this.veic_descricao = desc;
    // }

    public String getDescricao() {
        return this.veic_descricao;
    }

    // public void setValor(double valor) {
    // this.veic_valor = valor;
    // }

    public float getValor() {
        return this.veic_valor;
    }

    // public void setFoto(String foto) {
    // this.veic_fotos = foto;
    // }

    public String getFoto() {
        return this.veic_fotos;
    }
}