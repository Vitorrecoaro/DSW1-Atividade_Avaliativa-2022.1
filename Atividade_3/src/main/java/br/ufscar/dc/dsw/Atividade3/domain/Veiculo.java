package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "veiculo")
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @NotBlank
  @Size(max = 8)
  @Column(nullable = false, length = 8, unique = true)
  private String placa;

  @NotNull
  @NotBlank
  @Column(nullable = false)
  private String modelo;

  @NotNull
  @NotBlank
  @Column(nullable = false, unique = true)
  private String chassi;

  @NotNull
  @Max(value = 2022)
  @Column(nullable = false, length = 4)
  private int ano;

  @NotNull
  @Column(nullable = false)
  private float quilometragem;

  @Column(nullable = false)
  private String descricao;

  @NotNull
  @Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
  private float valor;

  @ManyToOne
  @JoinColumn(name = "cnpj")
  private Store loja;

  @OneToMany(mappedBy = "veiculo")
  private List<Proposta> propostas;

  private String path;
  private String path2;
  private String path3;
  private String path4;
  private String path5;
  private String path6;
  private String path7;
  private String path8;
  private String path9;
  private String path10;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getPath2() {
    return path2;
  }

  public void setPath2(String path2) {
    this.path2 = path2;
  }

  public String getPath3() {
    return path3;
  }

  public void setPath3(String path3) {
    this.path3 = path3;
  }

  public String getPath4() {
    return path4;
  }

  public void setPath4(String path4) {
    this.path4 = path4;
  }

  public String getPath5() {
    return path5;
  }

  public void setPath5(String path5) {
    this.path5 = path5;
  }

  public String getPath6() {
    return path6;
  }

  public void setPath6(String path6) {
    this.path6 = path6;
  }

  public String getPath7() {
    return path7;
  }

  public void setPath7(String path7) {
    this.path7 = path7;
  }

  public String getPath8() {
    return path8;
  }

  public void setPath8(String path8) {
    this.path8 = path8;
  }

  public String getPath9() {
    return path9;
  }

  public void setPath9(String path9) {
    this.path9 = path9;
  }

  public String getPath10() {
    return path10;
  }

  public void setPath10(String path10) {
    this.path10 = path10;
  }

  public Store getLoja() {
    return loja;
  }

  public void setLoja(Store lojaCNPJ) {
    this.loja = lojaCNPJ;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getChassi() {
    return chassi;
  }

  public void setChassi(String chassi) {
    this.chassi = chassi;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public float getQuilometragem() {
    return quilometragem;
  }

  public void setQuilometragem(float quilometragem) {
    this.quilometragem = quilometragem;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public float getValor() {
    return valor;
  }

  public void setValor(float valor) {
    this.valor = valor;
  }

  public Veiculo toVeiculo(Veiculo veiculo) {
    veiculo.setPlaca(this.placa);
    veiculo.setModelo(this.modelo);
    veiculo.setChassi(this.chassi);
    veiculo.setAno(this.ano);
    veiculo.setQuilometragem(this.quilometragem);
    veiculo.setDescricao(this.descricao);
    veiculo.setValor(this.valor);

    return veiculo;
  }

  public Veiculo toPath(Veiculo veiculo) {
    veiculo.setPath(this.path);
    veiculo.setPath2(this.path2);
    veiculo.setPath3(this.path3);
    veiculo.setPath4(this.path4);
    veiculo.setPath5(this.path5);
    veiculo.setPath6(this.path6);
    veiculo.setPath7(this.path7);
    veiculo.setPath8(this.path8);
    veiculo.setPath9(this.path9);
    veiculo.setPath10(this.path10);

    return veiculo;
  }
}
