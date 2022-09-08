package Atividade3.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import Atividade3.dao.IStoreDAO;
import Atividade3.dao.IVeiculoDAO;
import Atividade3.domain.Store;
import Atividade3.domain.Veiculo;

@RestController
public class RESTVeiculoController {

  @Autowired
  private IVeiculoDAO iVeiculoDAO;

  @Autowired
  private IStoreDAO storeDAO;

  private boolean isJSONValid(String jsonInString) {
    try {
      return new ObjectMapper().readTree(jsonInString) != null;
    } catch (IOException e) {
      return false;
    }
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
  public ResponseEntity<Veiculo> cria(@RequestBody JSONObject json, @PathVariable Long id) {
    try {
      if (isJSONValid(json.toString())) {
        Store loja = storeDAO.findById(id).get();
        Veiculo veiculo = new Veiculo();
        veiculo.jsonDecode(json);
        veiculo.setLoja(loja);
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

  // Exibi todos os veículos de modelo X
  @GetMapping("/api/veiculos/modelos/{modelo}")
  public ResponseEntity<List<Veiculo>> listaPorModelo(@PathVariable String modelo) {
    List<Veiculo> veiculos = this.iVeiculoDAO.findAllByModelo(modelo);
    if (veiculos.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(veiculos);
  }
}