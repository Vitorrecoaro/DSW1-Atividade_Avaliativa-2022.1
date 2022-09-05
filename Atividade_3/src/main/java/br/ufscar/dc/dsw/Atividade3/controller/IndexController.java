package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IUserDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.User;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Veiculo;

@Controller
public class IndexController {

    @Autowired
    private IVeiculoDAO iVeiculoDAO;

    @Autowired
    IUserDAO userDAO;

    public List<Veiculo> carros;

    @GetMapping("/")
    public ModelAndView index(Model model, Authentication auth) {
        ModelAndView mv = new ModelAndView("index");
        if (auth != null) {
            User user = userDAO.findByEmail(auth.getName());
            mv.addObject("typeUser", user.getTipo());
        }
        this.carros = iVeiculoDAO.findAll();
        mv.addObject("carros", carros);
        return mv;
    }

    @GetMapping("/redirect")
    public String redirect(Model model, Authentication auth) {
        if (auth != null) {
            User user = userDAO.findByEmail(auth.getName());
            switch (user.getTipo()) {
                case "ROLE_ADMIN":
                    return "redirect:/admin";
                case "ROLE_STORE":
                    return "redirect:/veiculos";
                case "ROLE_USER":
                    return "redirect:/usuario";
            }
        }
        model.addAttribute("error", "Você não está autenticado");
        return "index";
    }
}
