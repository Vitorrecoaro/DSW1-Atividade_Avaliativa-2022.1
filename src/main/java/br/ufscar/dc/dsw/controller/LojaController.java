package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import br.ufscar.dc.dsw.dao.LojaDAO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.Loja;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/loja/*")
public class LojaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LojaDAO dao;

    @Override
    public void init() {
        dao = new LojaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Loja loja = (Loja) request.getSession()
                .getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (loja == null) {
            response.sendRedirect(request.getContextPath());
        } else {
            String action = request.getPathInfo();
            if (action == null) {
                action = "";
            }

            try {
                switch (action) {
                    case "/cadastro":
                        formCadastroVeiculo(request, response);
                        break;
                    case "/veiculo/cadastro":
                        // cadastraUser(request, response);
                        break;
                    default:
                        RequestDispatcher dispatcher = request
                                .getRequestDispatcher(
                                        "/logado/loja/index.jsp");
                        dispatcher.forward(request, response);
                        break;
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
        }
    }

    private void formCadastroVeiculo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(
                        "/logado/loja/formCadastroVeiculo.jsp");
        dispatcher.forward(request, response);
    }
}
