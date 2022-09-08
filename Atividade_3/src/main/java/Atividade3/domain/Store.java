package Atividade3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.json.simple.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Atividade3.common.StoreDTO;

@Entity
@Table(name = "loja")
public class Store extends User {

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

    // @OneToMany(mappedBy = "loja")
    // private List<Veiculos> veiculos;

    // public List<Veiculo> getVeiculos() {
    // return veiculos;
    // }

    // public void setVeiculos(List<Veiculo> veiculos) {
    // this.veiculos = veiculos;
    // }

    public void toStore(StoreDTO dto) {
        this.cnpj = dto.getCnpj();
        this.descricao = dto.getDescricao();
        this.setNome(dto.getNome());
        this.setEmail(dto.getEmail());
        this.setSenha(dto.getSenha());
    }

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

        this.setCnpj((String) json.get("cnpj"));
        this.setDescricao((String) json.get("descricao"));
        this.setEmail((String) json.get("email"));
        this.setNome((String) json.get("nome"));
        this.setSenha(encoder.encode((String) json.get("senha")));
        this.setTipo("ROLE_STORE");

    }
}