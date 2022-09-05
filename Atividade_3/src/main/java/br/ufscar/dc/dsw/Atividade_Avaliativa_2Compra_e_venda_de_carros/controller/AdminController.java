package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.common.StoreDTO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.ICostumerDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IStoreDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Store;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Costumer;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IStoreDAO storeDAO;
    @Autowired
    private ICostumerDAO costumerDAO;

    private String passEncoder(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }

    @GetMapping("")
    public String adminHome(Model model) {
        model.addAttribute("users", costumerDAO.findAll());
        model.addAttribute("stores", storeDAO.findAll());
        return "admin/home";
    }

    // Métodos para usuários.

    @GetMapping("/usuario")
    public String formUser(Costumer user) {
        return "admin/formUser";
    }

    @PostMapping("/usuario")
    public String cadastraUser(@Valid Costumer usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Verifique o dados inseridos");
            return "admin/formUser";
        } else {
            try {
                usuario.setSenha(passEncoder(usuario.getSenha()));
                this.costumerDAO.save(usuario);
            } catch (Exception e) {
                model.addAttribute("error", "Já existe um usuário com essa combinação de dados");
                return "admin/formUser";
            }
            return "redirect:/admin/";
        }
    }

    @GetMapping("/usuario/{id}")
    public ModelAndView formEdicaoUser(Costumer costumer, @PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("admin/formEdicaoUser");
        Costumer user = this.costumerDAO.getReferenceById(id);
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/usuario/{id}")
    public String atualizaUser(@Valid Costumer usuario, BindingResult result, @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Verifique o dados inseridos");
            return "admin/formEdicaoUser";
        } else {
            try {
                usuario.setSenha(passEncoder(usuario.getSenha()));
                this.costumerDAO.save(usuario);
            } catch (Exception e) {
                model.addAttribute("error", "Já existe um usuário com essa combinação de dados");
                return "admin/formEdicaoUser";
            }
            return "redirect:/admin/";
        }
    }

    @GetMapping("/usuario/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        this.costumerDAO.deleteById(id);
        return "redirect:/admin/";
    }

    // Métodos para lojas.

    @GetMapping("/loja")
    public String formLoja(Store loja) {
        return "admin/formLoja";
    }

    @PostMapping("/loja")
    public String cadastraLoja(@Valid StoreDTO loja, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Verifique o dados inseridos");
            return "admin/formLoja";
        } else {
            try {
                Store novaLoja = new Store();
                novaLoja.toStore(loja);
                novaLoja.setTipo("ROLE_STORE");
                novaLoja.setSenha(passEncoder(novaLoja.getSenha()));
                this.storeDAO.save(novaLoja);
            } catch (Exception e) {
                model.addAttribute("error", "Já existe uma loja com essa combinação de dados");
                return "admin/formLoja";
            }
            return "redirect:/admin/";
        }
    }

    @GetMapping("/loja/{id}")
    public ModelAndView formEdicaoLoja(Store store, @PathVariable("id") long id) {
        Store loja = this.storeDAO.getReferenceById(id);
        ModelAndView mv = new ModelAndView("admin/formEdicaoLoja");
        mv.addObject("loja", loja);
        return mv;
    }

    @PostMapping("/loja/{id}")
    public String atualizaLoja(@Valid StoreDTO loja, BindingResult result, @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Verifique o dados inseridos");
            return "admin/formEdicaoLoja";
        } else {
            try {
                Store novaLoja = this.storeDAO.getReferenceById(id);
                novaLoja.toStore(loja);
                novaLoja.setSenha(passEncoder(novaLoja.getSenha()));
                this.storeDAO.save(novaLoja);
            } catch (Exception e) {
                model.addAttribute("error", "Já existe uma loja com essa combinação de dados");
                return "admin/formEdicaoLoja";
            }
            return "redirect:/admin/";
        }
    }

    @GetMapping("/loja/delete/{id}")
    public String deleteLoja(@PathVariable("id") long id) {
        this.storeDAO.deleteById(id);
        return "redirect:/admin/";
    }

}
