package Atividade3.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import Atividade3.dao.ICostumerDAO;
import Atividade3.dao.IPropostaDAO;
import Atividade3.domain.Costumer;
import Atividade3.domain.Proposta;

@CrossOrigin
@RestController
public class RESTCostumerController {

    @Autowired
    ICostumerDAO costumerDAO;

    @Autowired
    IPropostaDAO propostaDAO;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    @GetMapping("/api/clientes")
    public ResponseEntity<List<Costumer>> getClientes() {
        System.out.println("Entrou");
        List<Costumer> clientes = costumerDAO.findAll();
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @PostMapping(path = "/api/clientes")
    @ResponseBody
    public ResponseEntity<List<Costumer>> postCliente(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Costumer cliente = new Costumer();
                cliente.jsonDecode(json);
                costumerDAO.save(cliente);
                return ResponseEntity.ok(costumerDAO.findAll());
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping("/api/clientes/{id}")
    public ResponseEntity<Costumer> getCliente(@PathVariable Long id) {
        try {
            Costumer cliente = costumerDAO.findById(id).get();
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Costumer> updateCliente(@PathVariable Long id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Costumer cliente = costumerDAO.findById(id).get();
                cliente.jsonDecode(json);
                costumerDAO.save(cliente);
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @DeleteMapping("/api/clientes/{id}")
    public ResponseEntity<Boolean> deleteCliente(@PathVariable Long id) {
        try {
            Costumer usuario = costumerDAO.findById(id).get();
            if (usuario == null) {
                return ResponseEntity.notFound().build();
            } else {
                int i = 0;
                List<Proposta> propostasUsuario = propostaDAO.findAllByUsuario(usuario);

                // Apaga todos os veículos da loja.
                for (i = 0; i < propostasUsuario.size(); i++) {
                    this.propostaDAO.delete(propostasUsuario.get(i));
                }

                costumerDAO.delete(usuario);
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

}
