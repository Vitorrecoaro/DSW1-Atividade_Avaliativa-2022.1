package Atividade3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Atividade3.dao.IStoreDAO;
import Atividade3.domain.Store;

@CrossOrigin
@RestController
public class RESTStoreController {

    @Autowired
    IStoreDAO storeDao;

    @GetMapping("/api/loja")
    public ResponseEntity<List<Store>> getLojas() {
        List<Store> lojas = storeDao.findAll();
        if (lojas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lojas);
    }

    // @PostMapping("/api/loja")
    // public ResponseEntity<Store> postLoja(){

    // }
}
