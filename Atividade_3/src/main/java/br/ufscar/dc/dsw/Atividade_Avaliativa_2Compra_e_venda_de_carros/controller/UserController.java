package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IPropostaDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IUserDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Proposta;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.User;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Veiculo;

@Controller
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private IVeiculoDAO iVeiculoDAO;

    @Autowired
    IPropostaDAO propostaDAO;

    @Autowired
    IUserDAO userDAO;

    @GetMapping("")
    public ModelAndView homePage(Authentication auth) {
        ModelAndView mv = new ModelAndView("user/home");
        User user = userDAO.findByEmail(auth.getName());
        List<Proposta> propostasFeitas = propostaDAO.findAllByUsuario(user);
        mv.addObject("propostas", propostasFeitas);
        mv.addObject("listaVeiculos", iVeiculoDAO.findAll());
        return mv;
    }

    @GetMapping("/proposta/{id}")
    public String formProposta(Veiculo veiculo, Model model, @PathVariable("id") Long id, Proposta proposta) {
        Veiculo veic = iVeiculoDAO.getReferenceById(id);
        model.addAttribute("carro", veic);
        return "user/formProposta";
    }

    @PostMapping("/proposta/{id}")
    public String fazerProposta(Veiculo veiculo, Model model, @PathVariable("id") Long id, Proposta proposta,
            Authentication auth) {
        User user = userDAO.findByEmail(auth.getName());
        Veiculo veic = iVeiculoDAO.getReferenceById(id);
        proposta.setStatus("ABERTO");
        proposta.setUsuario(user);
        proposta.setVeiculo(veic);
        LocalDate utilDate = LocalDate.now();
        Date now = Date.valueOf(utilDate);
        proposta.setData_proposta(now);
        propostaDAO.save(proposta);

        return "redirect:/usuario";
    }
}
