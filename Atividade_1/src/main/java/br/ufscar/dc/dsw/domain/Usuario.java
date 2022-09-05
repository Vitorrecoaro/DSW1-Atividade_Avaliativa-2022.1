package br.ufscar.dc.dsw.domain;

public class Usuario {

	private Long id;
	private String nome;
	private String senha;
	private String email;
	private String tipo_de_usuario;

	public Usuario(String nome, String senha, String email, String tipo) {
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.tipo_de_usuario = tipo;
	}

	public Usuario(
			Long id, String nome, String senha, String email, String tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
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

	public void setTipo(String tipo) {
		if (tipo.length() == 10)
			this.tipo_de_usuario = tipo;
	}

	public String getTipo() {
		return this.tipo_de_usuario;
	}
}
