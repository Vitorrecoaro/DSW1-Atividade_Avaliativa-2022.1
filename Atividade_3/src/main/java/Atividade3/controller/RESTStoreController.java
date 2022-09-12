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

import Atividade3.dao.IPropostaDAO;
import Atividade3.dao.IStoreDAO;
import Atividade3.dao.IVeiculoDAO;
import Atividade3.domain.Proposta;
import Atividade3.domain.Store;
import Atividade3.domain.Veiculo;

@CrossOrigin
@RestController
public class RESTStoreController {

    @Autowired
    IStoreDAO storeDao;

    @Autowired
    IVeiculoDAO veiculoDAO;

    @Autowired
    IPropostaDAO propostaDAO;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    @GetMapping("/api/lojas")
    public ResponseEntity<List<Store>> getLojas() {
        List<Store> lojas = storeDao.findAll();
        if (lojas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lojas);
    }

    @PostMapping(path = "/api/lojas")
    @ResponseBody
    public ResponseEntity<List<Store>> postLoja(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Store loja = new Store();
                loja.jsonDecode(json);
                storeDao.save(loja);
                return ResponseEntity.ok(storeDao.findAll());
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping("/api/lojas/{id}")
    public ResponseEntity<Store> getLoja(@PathVariable Long id) {
        try {
            Store loja = storeDao.findById(id).get();
            return ResponseEntity.ok(loja);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/lojas/{id}")
    @ResponseBody
    public ResponseEntity<Store> updateLoja(@PathVariable Long id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Store loja = storeDao.findById(id).get();
                loja.jsonDecode(json);
                storeDao.save(loja);
                return ResponseEntity.ok(loja);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @DeleteMapping("/api/lojas/{id}")
    public ResponseEntity<Boolean> deleteLoja(@PathVariable Long id) {
        try {

            Store loja = storeDao.findById(id).get();
            if (loja == null) {
                return ResponseEntity.notFound().build();
            } else {
                List<Veiculo> veiculosLoja = this.veiculoDAO.findAllByLoja(loja);
                int i = 0;

                // Apaga todos os veículos da loja.
                for (i = 0; i < veiculosLoja.size(); i++) {
                    int j = 0;
                    List<Proposta> propostasVeiculos = this.propostaDAO.findAllByVeiculo(veiculosLoja.get(i));

                    // Apaga todas as propostas de cada veículo.
                    for (j = 0; j < propostasVeiculos.size(); j++) {
                        this.propostaDAO.delete(propostasVeiculos.get(j));
                    }

                    this.veiculoDAO.delete(veiculosLoja.get(i));
                }
                storeDao.delete(loja);
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);

        }
    }
}
