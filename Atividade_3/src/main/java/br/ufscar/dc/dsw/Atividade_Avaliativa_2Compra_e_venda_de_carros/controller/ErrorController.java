package br.ufscar.dc.dsw.Atividade_Avaliativa_2Compra_e_venda_de_carros.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ErrorController implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {

        ModelAndView model = new ModelAndView("error");
        model.addObject("status", status.value());
        switch (status.value()) {
            case 403:
                model.addObject("error", "Proibido");
                model.addObject("message", "Você está tentando acessar uma página que não tem permissão para acessar.");
                break;
            case 404:
                model.addObject("error", "Não encontrado");
                model.addObject("message", "Não foi possível encontrar o recurso solicitado.");
                break;
            default:
                model.addObject("error", "Erro");
                model.addObject("message", "Algo de errado não está certo! Contate a equipe.");
                break;
        }
        return model;
    }
}