package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.LojaDAO;
import br.ufscar.dc.dsw.dao.VeiculoDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/veiculos/*")
public class VeiculoController extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private VeiculoDAO dao;
  private LojaDAO daoLoja;

  @Override
  public void init() {
    dao = new VeiculoDAO();
    daoLoja = new LojaDAO();
  }

  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getPathInfo();
    if (action == null) {
      action = "";
    }

    try {
      switch (action) {
        // case "/novoVeiculo":
        // apresentaFormCadastro(request, response);
        // break;
        // case "/insercao":
        // insere(request, response);
        // break;
        default:
          lista(request, response);
          break;
      }
    } catch (RuntimeException | IOException | ServletException e) {
      throw new ServletException(e);
    }
  }

  private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Veiculo> listaVeiculos = null;
    Erro erros = new Erro();
    Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
    Loja lojaLogada = (Loja) request.getSession().getAttribute("lojaLogada");

    if (usuarioLogado != null && lojaLogada == null) {
      listaVeiculos = dao.getByCpf(usuarioLogado.getCpf());
    } else if (usuarioLogado == null && lojaLogada != null) {
      listaVeiculos = dao.getByCnpj(lojaLogada.getCnpj());
    }

    request.setAttribute("listaVeiculos", listaVeiculos);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/veiculos/index.jsp");
    dispatcher.forward(request, response);

  }
}
