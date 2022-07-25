package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Usuario {

	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private String CPF;
	private String telefone;
	private char sexo;
	private Date data_de_nasc;
	private String tipo_de_usuario;

	public Usuario(
			String nome, String login, String senha, String email,
			String CPF, String telefone, char sexo, Date nasc, String tipo) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.CPF = CPF;
		this.telefone = telefone;
		this.sexo = sexo;
		this.data_de_nasc = nasc;
		this.tipo_de_usuario = tipo;
	}

	public Usuario(
			Long id, String nome, String login, String senha, String email,
			String CPF, String telefone, char sexo, Date nasc, String tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.CPF = CPF;
		this.telefone = telefone;
		this.sexo = sexo;
		this.data_de_nasc = nasc;
		this.tipo_de_usuario = tipo;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String password) {
		this.senha = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
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
