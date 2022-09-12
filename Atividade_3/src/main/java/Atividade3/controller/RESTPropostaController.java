package Atividade3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

  // Exibi todas as Propostas de um veiculo
  @GetMapping("/api/propostas/veiculos/{id}")
  public ResponseEntity<List<Proposta>> listaPropPorVeic(@PathVariable Long id) {
    try {
      Veiculo veiculo = iVeiculoDAO.findById(id).get();
      List<Proposta> propostas = this.propostaDAO.findAllByVeiculo(veiculo);
      if (propostas.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(propostas);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
    }
  }

  // Exibi todas as Propostas de um cliente
  @GetMapping("/api/propostas/clientes/{id}")
  public ResponseEntity<List<Proposta>> listaPropPorCliente(@PathVariable Long id) {
    try {
      Costumer costumer = costumerDAO.findById(id).get();
      List<Proposta> propostas = this.propostaDAO.findAllByUsuario(costumer);
      if (propostas.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(propostas);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
    }
  }

}
