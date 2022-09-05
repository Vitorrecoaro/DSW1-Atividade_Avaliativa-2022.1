package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import br.ufscar.dc.dsw.dao.LojaDAO;
import br.ufscar.dc.dsw.dao.PropostaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.VeiculoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.Loja;
import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Veiculo;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/loja/*")
public class LojaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LojaDAO lojaDao;
    private UsuarioDAO usuarioDao;
    private Loja loja;
    private PropostaDAO propostaDAO;
    private List<Proposta> listaPropostas;
    private Usuario usuario = null;
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    @Override
    public void init() {
        lojaDao = new LojaDAO();
        usuarioDao = new UsuarioDAO();
        propostaDAO = new PropostaDAO();
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

        this.usuario = (Usuario) request.getSession()
                .getAttribute("usuarioLogado");
        this.loja = lojaDao.getbyEmail(usuario.getEmail());
        Erro erros = new Erro();

        if (this.usuario == null) {
            response.sendRedirect(request.getContextPath());
        } else {
            this.listaPropostas = propostaDAO.getAllByCNPJ(this.loja);
            String action = request.getPathInfo();
            if (action == null) {
                action = "";
            }
            try {
                switch (action) {
                    case "/cadastrarVeiculo":
                        formCadastroVeiculo(request, response);
                        break;
                    case "/novoVeiculo":
                        createVeiculo(request, response);
                        break;
                    default:
                        RequestDispatcher dispatcher = request
                                .getRequestDispatcher(
                                        "/logado/loja/index.jsp");
                        request.setAttribute("listaPropostas", listaPropostas);
                        List<Veiculo> listaVeiculos = veiculoDAO.getAllbyLoja(loja);
                        request.setAttribute("listaVeiculos", listaVeiculos);
                        dispatcher.forward(request, response);
                        break;
                }
            } catch (RuntimeException | IOException | ServletException e) {
                throw new ServletException(e);
            }
        }
    }

    private void createVeiculo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String placa = request.getParameter("placa");
        String modelo = request.getParameter("modelo");
        String chassi = request.getParameter("chassi");
        int ano = Integer.parseInt((request.getParameter("ano")));
        int quilometragem = Integer.parseInt(request.getParameter("quilometragem"));
        String descricao = request.getParameter("descricao");
        int valor = Integer.parseInt(request.getParameter("valor"));
        String fotos = request.getParameter("fotos");

        try {
            Veiculo novoVeic = new Veiculo(
                    placa, loja.getCNPJ(), modelo, chassi, ano, quilometragem,
                    descricao, valor, fotos);
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculoDAO.insert(novoVeic);
        } catch (Exception e) {
            Erro erros = new Erro();
            erros.add("Erro nos dados preenchidos.");

            request.setAttribute("mensagens", erros);

            RequestDispatcher rd = request
                    .getRequestDispatcher("/loja/cadastrarVeiculo");
            rd.forward(request, response);
        }
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/loja");
        dispatcher.forward(request, response);
    }

    private void formCadastroVeiculo(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(
                        "/logado/loja/formCadastroVeiculo.jsp");
        request.setAttribute("loja", loja);
        request.setAttribute("usuario", usuario);
        dispatcher.forward(request, response);
    }
}
