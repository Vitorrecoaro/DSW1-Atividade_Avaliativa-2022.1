package Atividade3.domain;

import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.json.simple.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    // @OneToMany(mappedBy = "usuario")
    // private List<Proposta> listProposta;

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

    // public List<Proposta> getListProposta() {
    // return listProposta;
    // }

    // public void setListProposta(List<Proposta> listProposta) {
    // this.listProposta = listProposta;
    // }

    public void jsonDecode(JSONObject json) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Object id = json.get("id");

        if (id != null) {
            if (id instanceof Integer) {
                this.setId(((Integer) id).longValue());
            } else {
                this.setId((Long) id);
            }
        }

        this.setEmail((String) json.get("email"));
        this.setNome((String) json.get("nome"));
        this.setSenha(encoder.encode((String) json.get("senha")));
        this.setTipo((String) json.get("tipo"));
        this.setCpf((String) json.get("cpf"));
        this.setTelefone((String) json.get("telefone"));
        this.setSexo(((String) json.get("sexo")).charAt(0));
        this.setDataNasc(Date.valueOf((String) json.get("dataNasc")));
    }

}