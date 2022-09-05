package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IPropostaDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IStoreDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IUserDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Proposta;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Store;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.User;
import br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.domain.Veiculo;

@Controller
@RequestMapping("/veiculos")
public class StoreController {

    @Autowired
    private IVeiculoDAO iVeiculoDAO;

    @Autowired
    private IStoreDAO storeDAO;

    @Autowired
    private IPropostaDAO propostaDAO;

    @Autowired
    private IUserDAO userDAO;

    // Lista os veiculos cadastrados
    @GetMapping("")
    public ModelAndView listar(Authentication auth) {
        ModelAndView mv = new ModelAndView("store/index");
        if (auth != null) {
            User user = userDAO.findByEmail(auth.getName());
            Store loja = storeDAO.findByEmail(user.getEmail());
            List<Veiculo> veiculos = this.iVeiculoDAO.findAllByLoja(loja);
            mv.addObject("veiculos", veiculos);
        }
        return mv;
    }

    // Cadastra novo veículo
    @GetMapping("/cadastrar")
    public String cadastrar(Veiculo veiculo) {
        return "store/cadastrar";
    }

    // Salva os veiculo cadastrado caso não haja erro
    @PostMapping("")
    public String cadastrado(@Valid Veiculo veiculo, BindingResult bindingResult, RedirectAttributes attr,
            Authentication auth, Model model) {
        if (bindingResult.hasErrors()) {
            return "store/cadastrar";
        } else {
            if (auth != null) {
                User user = userDAO.findByEmail(auth.getName());
                Store loja = storeDAO.findByEmail(user.getEmail());
                veiculo.setLoja(loja);
            }
            try {
                this.iVeiculoDAO.save(veiculo);
                attr.addFlashAttribute("sucess", "Cadastro de veículos feito com sucesso");
                return "redirect:/veiculos";
            } catch (Exception e) {
                model.addAttribute("error", "Já existe um veículo com estes dados.");
                return "store/cadastrar";
            }

        }
    }

    // Exibi os detalhes do carro e as propostas
    @GetMapping("/{id}")
    public ModelAndView propostas(@PathVariable Long id) {
        Optional<Veiculo> optional = this.iVeiculoDAO.findById(id);

        if (optional.isPresent()) {
            Veiculo veiculo = optional.get();
            ModelAndView mv = new ModelAndView("store/detalhes");
            mv.addObject("veiculo", veiculo);

            return mv;
        }
        // Caso não encontre um registo na tabela
        else {
            return new ModelAndView("redirect:/veiculos");
        }
    }

    // Editar veiculo
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id, Veiculo veiculo) {
        Optional<Veiculo> optional = this.iVeiculoDAO.findById(id);

        if (optional.isPresent()) {
            veiculo = optional.get();
            ModelAndView mv = new ModelAndView("store/editar");
            mv.addObject("veiculo", veiculo);

            return mv;
        }
        // Caso não encontre um registo na tabela
        else {
            return new ModelAndView("redirect:/veiculos");
        }
    }

    // Salva o Veiculo editado caso não haja erro
    @PostMapping("/{id}")
    public ModelAndView editado(@PathVariable Long id, @Valid Veiculo veiculo, BindingResult bindingResult,
            RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("store/editar");
            return mv;

        } else {
            Optional<Veiculo> optional = this.iVeiculoDAO.findById(id);
            attr.addFlashAttribute("sucess", "Criação de veículo feita com sucesso.");

            if (optional.isPresent()) {
                veiculo = veiculo.toVeiculo(optional.get());
                this.iVeiculoDAO.save(veiculo);

                return new ModelAndView("redirect:/veiculos/" + veiculo.getId());

            } else {
                return new ModelAndView("redirect:/veiculos");
            }
        }

    }

    // Excluir Veiculos
    @GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/veiculos");

        try {
            this.iVeiculoDAO.deleteById(id);
            mv.addObject("mensagem", "Veiculo " + id + " excluido com sucesso!");
            mv.addObject("erro", false);

        } catch (EmptyResultDataAccessException e) {
            mv.addObject("mensagem", "Veiculo " + id + " não foi encontrado!");
            mv.addObject("erro", true);
        }

        return mv;
    }

    // ******************IMAGENS*******************

    // Lista as imagens cadastrados
    @GetMapping("{id}/imagens")
    public ModelAndView listarImagem(@PathVariable Long id) {
        Optional<Veiculo> optional = this.iVeiculoDAO.findById(id);

        if (optional.isPresent()) {
            Veiculo veiculo = optional.get();
            ModelAndView mv = new ModelAndView("imagem/index");
            mv.addObject("veiculo", veiculo);

            return mv;
        }
        // Caso não encontre um registo na tabela
        else {
            return new ModelAndView("redirect:/veiculos");
        }
    }

    // Cadastra e edita as imagens
    @GetMapping("/{id}/novaImagem")
    public ModelAndView cadastrarImagem(@PathVariable Long id, Veiculo veiculo) {
        Optional<Veiculo> optional = this.iVeiculoDAO.findById(id);

        if (optional.isPresent()) {
            veiculo = optional.get();
            ModelAndView mv = new ModelAndView("imagem/cadastro");
            mv.addObject("veiculo", veiculo);

            return mv;
        }
        // Caso não encontre um registo na tabela
        else {
            return new ModelAndView("redirect:/veiculos");
        }
    }

    // Salva as imagens
    @PostMapping("/{id}/imagens")
    public ModelAndView salvarImagem(@PathVariable Long id, Veiculo veiculo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("imagem/cadastro");
            return mv;

        } else {
            Optional<Veiculo> optional = this.iVeiculoDAO.findById(id);

            if (optional.isPresent()) {
                veiculo = veiculo.toPath(optional.get());
                this.iVeiculoDAO.save(veiculo);

                return new ModelAndView("redirect:/veiculos/" + veiculo.getId() + "/imagens");

            } else {
                return new ModelAndView("redirect:/veiculos");
            }
        }
    }

    // Exibi os detalhes do carro após adicionar imagens
    @GetMapping("/{id}/ok")
    public ModelAndView ok(@PathVariable Long id) {
        Optional<Veiculo> optional = this.iVeiculoDAO.findById(id);

        if (optional.isPresent()) {
            Veiculo veiculo = optional.get();
            ModelAndView mv = new ModelAndView("store/detalhes");
            mv.addObject("veiculo", veiculo);

            return mv;
        }
        // Caso não encontre um registo na tabela
        else {
            return new ModelAndView("redirect:/veiculos");
        }
    }

    @GetMapping("/{id}/propostas")
    public ModelAndView propostasPorVeiculo(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("proposta/propostasPorVeiculo");
        Veiculo veic = iVeiculoDAO.getReferenceById(id);
        List<Proposta> propostas = propostaDAO.findAllByVeiculo(veic);
        if (propostas.size() > 0) {
            mv.addObject("propostas", propostas);
        } else {
            mv.addObject("error", "Não há propostas para este veículo");
            mv.setViewName("store/detalhes");
            mv.addObject("veiculo", veic);
        }
        return mv;
    }

    @GetMapping("/proposta/{id}")
    public ModelAndView formRespostaLoja(@PathVariable("id") Long id, Proposta proposta) {
        ModelAndView mv = new ModelAndView("proposta/respostaProposta");
        Proposta prop = propostaDAO.getReferenceById(id);
        mv.addObject("proposta", prop);
        return mv;
    }

    @PostMapping("/proposta/{id}")
    public ModelAndView atualizaProposta(@PathVariable("id") Long id, Proposta proposta, String status) {
        ModelAndView mv = new ModelAndView("redirect:/veiculos/" + id + "/propostas");
        Proposta prop = propostaDAO.getReferenceById(id);
        prop.setStatus(status);
        propostaDAO.save(prop);
        return mv;
    }

}
