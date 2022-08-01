package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.PessoaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.VeiculoDAO;
import br.ufscar.dc.dsw.domain.Pessoa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Veiculo;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout" })

public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		if (request.getParameter("bOK") != null) {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			if (login == null || login.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				UsuarioDAO dao = new UsuarioDAO();
				Usuario usuario = dao.getbyEmail(login);
				if (usuario != null) {
					if (usuario.getSenha().equals(senha)) {
						request.getSession()
								.setAttribute("usuarioLogado", usuario);
						if (usuario.getTipo().equals("PESSOA")) {
							PessoaDAO pessoaDAO = new PessoaDAO();
							Pessoa pessoa = pessoaDAO.getbyEmail(login);
							if (pessoa.getTipo().equals("ADMIN")) {
								response.sendRedirect("admin/");
							} else {
								response.sendRedirect("usuario/");
							}
						} else if (usuario.getTipo().equals("LOJA")) {
							response.sendRedirect("loja/");
						}
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		VeiculoDAO veiculoDao = new VeiculoDAO();
		List<Veiculo> listaVeiculos = veiculoDao.getAll();
		request.getSession().invalidate();
		request.setAttribute("mensagens", erros);
		request.setAttribute("listaVeiculos", listaVeiculos);

		String URL = "/ListaVeiculos.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
}