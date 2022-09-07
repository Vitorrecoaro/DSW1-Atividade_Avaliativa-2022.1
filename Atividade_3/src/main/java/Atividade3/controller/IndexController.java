package Atividade3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import Atividade3.dao.IUserDAO;
import Atividade3.dao.IVeiculoDAO;
import Atividade3.domain.User;
import Atividade3.domain.Veiculo;

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
