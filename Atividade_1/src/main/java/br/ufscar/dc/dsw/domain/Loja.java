package br.ufscar.dc.dsw.domain;

public class Loja {

    private Long id;
    private String CNPJ;
    private String descricao;
    private Usuario usuario;

    public Loja(Long id, String CNPJ, Usuario user, String descricao) {
        if (CNPJ.length() == 14) {
            this.id = id;
            this.CNPJ = CNPJ;
            this.descricao = descricao;
            this.usuario = user;
        }
    }

    public Loja(String CNPJ, Usuario user, String descricao) {
        if (CNPJ.length() == 14) {
            this.CNPJ = CNPJ;
            this.usuario = user;
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
        return usuario.getEmail();
    }

    // public void setEmail(String email) {
    // this.email = email;
    // }

    public String getSenha() {
        return usuario.getSenha();
    }

    // public void setSenha(String senha) {
    // this.senha = senha;
    // }

    public String getNome() {
        return usuario.getNome();
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

    public Usuario getUsuario() {
        return this.usuario;
    }
}
