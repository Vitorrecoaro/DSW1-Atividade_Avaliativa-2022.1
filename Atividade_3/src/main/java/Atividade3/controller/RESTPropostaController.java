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

import Atividade3.dao.ICostumerDAO;
import Atividade3.dao.IPropostaDAO;
import Atividade3.dao.IVeiculoDAO;
import Atividade3.domain.Costumer;
import Atividade3.domain.Proposta;
import Atividade3.domain.Veiculo;

@RestController
public class RESTPropostaController {

  @Autowired
  private IPropostaDAO propostaDAO;

  @Autowired
  private IVeiculoDAO iVeiculoDAO;

  @Autowired
  private ICostumerDAO costumerDAO;

  private boolean isJSONValid(String jsonInString) {
    try {
      return new ObjectMapper().readTree(jsonInString) != null;
    } catch (IOException e) {
      return false;
    }
  }

  // Exibi todas as Propostas de um veiculo
  @GetMapping("/api/propostas/veiculos/{id}")
  public ResponseEntity<List<Proposta>> listaPropPorVeic(@PathVariable Long id) {
    Veiculo veiculo = iVeiculoDAO.findById(id).get();
    List<Proposta> propostas = this.propostaDAO.findAllByVeiculo(veiculo);
    if (propostas.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(propostas);
  }

  // Exibi todas as Propostas de um cliente
  @GetMapping("/api/propostas/clientes/{id}")
  public ResponseEntity<List<Proposta>> listaPropPorCliente(@PathVariable Long id) {
    Costumer costumer = costumerDAO.findById(id).get();
    List<Proposta> propostas = this.propostaDAO.findAllByUsuario(costumer);
    if (propostas.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(propostas);
  }

}
