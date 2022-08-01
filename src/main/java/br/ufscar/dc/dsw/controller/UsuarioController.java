package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/usuario/*")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao;

	@Override
	public void init() {
		dao = new UsuarioDAO();
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

		Usuario usuario = (Usuario) request.getSession()
				.getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
		} else if (usuario.getTipo().equals("PESSOA")) {
			request.setAttribute("usuarioLogado", usuario);
			String action = request.getPathInfo();
			if (action == null) {
				action = "";
			}

			try {
				switch (action) {
					case "/painel":
						painelUsuario(request, response);
						break;
					default:
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/index.jsp");
						dispatcher.include(request, response);
				}
			} catch (RuntimeException | IOException | ServletException e) {
				throw new ServletException(e);
			}

		} else {
			erros.add("Acesso não autorizado!");
			erros.add("Faça o login para acessar esta página.");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request
					.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
		}
	}

	private void painelUsuario(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/logado/usuario/index.jsp");
		dispatcher.include(request, response);
	}
}