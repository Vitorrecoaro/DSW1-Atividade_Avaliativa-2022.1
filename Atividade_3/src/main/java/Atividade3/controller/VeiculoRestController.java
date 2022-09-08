package Atividade3.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import Atividade3.dao.IPropostaDAO;
import Atividade3.dao.IStoreDAO;
import Atividade3.dao.IUserDAO;
import Atividade3.dao.IVeiculoDAO;
import Atividade3.domain.Proposta;
import Atividade3.domain.Store;
import Atividade3.domain.User;
import Atividade3.domain.Veiculo;

@RestController
public class VeiculoRestController {

  @Autowired
  private IVeiculoDAO iVeiculoDAO;

  @Autowired
  private IStoreDAO storeDAO;

  @Autowired
  private IPropostaDAO propostaDAO;

  @Autowired
  private IUserDAO userDAO;

  private boolean isJSONValid(String jsonInString) {
    try {
      return new ObjectMapper().readTree(jsonInString) != null;
    } catch (IOException e) {
      return false;
    }
  }

  // Não entendi o que faz
  @SuppressWarnings("unchecked")
  private void parse(Store loja, JSONObject json) {

    Map<String, Object> map = (Map<String, Object>) json.get("loja");

    Object id = map.get("id");
    if (id instanceof Integer) {
      loja.setId(((Integer) id).longValue());
    } else {
      loja.setId(((Long) id));
    }

    loja.setCnpj((String) map.get("cnpj"));
    loja.setNome((String) map.get("nome"));
  }

  private void parse(Veiculo veiculo, JSONObject json) {

    Object id = json.get("id");
    if (id != null) {
      if (id instanceof Integer) {
        veiculo.setId(((Integer) id).longValue());
      } else {
        veiculo.setId(((Long) id));
      }
    }

    veiculo.setPlaca((String) json.get("placa"));
    veiculo.setModelo((String) json.get("modelo"));
    veiculo.setChassi((String) json.get("chassi"));
    veiculo.setAno((Integer) json.get("ano"));
    veiculo.setQuilometragem((Float) json.get("quilometragem"));
    veiculo.setDescricao((String) json.get("descricao"));
    Float value = (Float) json.get("valor");
    veiculo.setValor(Float.valueOf(value));

    Store loja = new Store();
    parse(loja, json);
    veiculo.setLoja(loja);
  }

  // Exibi todos os veiculos de uma loja X
  @GetMapping(path = "/api/veiculos/lojas/{id}")
  public ResponseEntity<List<Veiculo>> lista(@PathVariable("id") Long id) {
    Store loja = storeDAO.findById(id).get();
    List<Veiculo> veiculos = this.iVeiculoDAO.findAllByLoja(loja);
    if (veiculos.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(veiculos);
  }

  // Cria um novo veiculo em uma loja X
  @PostMapping(path = "/api/veiculos/lojas/{id}")
  @ResponseBody
  public ResponseEntity<Veiculo> cria(@RequestBody JSONObject json) {
    try {
      if (isJSONValid(json.toString())) {
        Veiculo veiculo = new Veiculo();
        parse(veiculo, json);
        this.iVeiculoDAO.save(veiculo);
        return ResponseEntity.ok(veiculo);
      } else {
        return ResponseEntity.badRequest().body(null);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
    }
  }
}