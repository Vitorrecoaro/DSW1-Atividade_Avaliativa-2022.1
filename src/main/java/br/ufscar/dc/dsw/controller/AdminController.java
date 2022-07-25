package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		} else if (usuario.getTipo().equals("ADMIN")) {
			String action = request.getPathInfo();
			if (action == null) {
				action = "";
			}

			try {
				switch (action) {
					case "/cadastro":
						formCadastroUser(request, response);
						break;
					case "/user/cadastro":
						cadastraUser(request, response);
						break;
					case "/user/remove":
						removeUser(request, response);
						break;
					case "/atualizacao":
						formAtualizacaoUser(request, response);
						break;
					case "/user/atualizacao":
						updateUser(request, response);
						break;
					default:
						RequestDispatcher dispatcher = request.getRequestDispatcher(
								"/logado/admin/index.jsp");
						dispatcher.forward(request, response);
						break;
				}
			} catch (RuntimeException | IOException | ServletException e) {
				throw new ServletException(e);
			}

		} else {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request
					.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
		}
	}

	private void formCadastroUser(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formCadastroUser.jsp");
		dispatcher.forward(request, response);
	}

	private void cadastraUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cpf = request.getParameter("cpf");
		String login = request.getParameter("login");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		char sexo = request.getParameter("sexo").charAt(0);
		String telefone = request.getParameter("telefone");
		String tipo = request.getParameter("tipo");
		if (tipo == null || tipo.equals("")) {
			tipo = "USER";
		}
		Date dataNascimento = Date.valueOf(LocalDate.now());
		try {
			dataNascimento = Date.valueOf(LocalDate.parse(request.getParameter("dataNascimento")));
		} catch (Exception e) {
			dataNascimento = Date.valueOf(LocalDate.now());
		}

		try {
			Usuario usuario = new Usuario(nome, login, senha, email, cpf,
					telefone, sexo, dataNascimento, tipo);
			UsuarioDAO dao = new UsuarioDAO();
			dao.insert(usuario);
		} catch (Exception e) {
			Erro erros = new Erro();
			erros.add("Erro nos dados preenchidos.");

			request.setAttribute("mensagens", erros);

			RequestDispatcher rd = request.getRequestDispatcher("/usuario/formCadastro.jsp");
			rd.forward(request, response);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	private void removeUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

		if (usuarioLogado == null) {
			erros.add("Precisa estar logado para acessar essa página.");

			request.setAttribute("mensagens", erros);
			String URL = "/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(URL);
			rd.forward(request, response);
			return;
		} else if (!usuarioLogado.getTipo().equals("ADMIN")) {
			erros.add("Não possui permissão de acesso.");
			erros.add("Apenas [ADMIN] pode acessar essa página.");

			request.setAttribute("mensagens", erros);
			String URL = "/noAuth.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(URL);
			rd.forward(request, response);
			return;
		}

		Long id = Long.parseLong(request.getParameter("user_id"));
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.getbyID(id);
		dao.delete(usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario");
		dispatcher.forward(request, response);

	}

	private void formAtualizacaoUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

		if (usuarioLogado == null) {
			erros.add("Precisa estar logado para acessar essa página.");

			request.setAttribute("mensagens", erros);
			String URL = "/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(URL);
			rd.forward(request, response);
			return;
		} else if (!usuarioLogado.getTipo().equals("ADMIN")) {
			erros.add("Não possui permissão de acesso.");
			erros.add("Apenas [ADMIN] pode acessar essa página.");

			request.setAttribute("mensagens", erros);
			String URL = "/noAuth.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(URL);
			rd.forward(request, response);
			return;
		}

		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("user_id"));
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.getbyID(id);
		request.setAttribute("usuario", usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/usuario/formEdicao.jsp");
		dispatcher.forward(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

		if (usuarioLogado == null) {
			erros.add("Precisa estar logado para acessar essa página.");

			request.setAttribute("mensagens", erros);
			String URL = "/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(URL);
			rd.forward(request, response);
			return;
		} else if (!usuarioLogado.getTipo().equals("ADMIN")) {
			erros.add("Não possui permissão de acesso.");
			erros.add("Apenas [ADMIN] pode acessar essa página.");

			request.setAttribute("mensagens", erros);
			String URL = "/noAuth.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(URL);
			rd.forward(request, response);
			return;
		}

		request.setCharacterEncoding("UTF-8");

		Long id = Long.parseLong(request.getParameter("user_id"));
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.getbyID(id);

		String CPF = request.getParameter("CPF");
		if (CPF == "") {
			CPF = usuario.getCPF();
		}

		String login = request.getParameter("login");
		if (login == "") {
			login = usuario.getLogin();
		}

		String nome = request.getParameter("nome");
		if (nome == "") {
			nome = usuario.getNome();
		}
		String email = request.getParameter("email");
		if (email == "") {
			email = usuario.getEmail();
		}
		String senha = request.getParameter("senha");
		if (senha == "") {
			senha = usuario.getSenha();
		}
		char sexo = request.getParameter("sexo").charAt(0);
		if (sexo == '\0' || sexo == ' ') {
			sexo = usuario.getSexo();
		}

		String telefone = request.getParameter("telefone");
		if (telefone == "") {
			telefone = usuario.getTelefone();
		}

		String tipo = request.getParameter("tipo");
		if (tipo == "") {
			tipo = usuario.getTipo();

			Date dataNascimento = usuario.getDataNasc();
			try {
				dataNascimento = Date.valueOf(LocalDate.parse(request.getParameter("dataNascimento")));
			} catch (Exception e) {
				dataNascimento = usuario.getDataNasc();
			}

			Usuario usuarioAtualizado = new Usuario(nome, login, senha,
					email, CPF, telefone, sexo, dataNascimento, tipo);
			try {
				dao.update(usuarioAtualizado);
			} catch (Exception e) {
				erros.add("Erro nos dados preenchidos.");

				request.setAttribute("mensagens", erros);

				RequestDispatcher rd = request.getRequestDispatcher("/usuario/formEdicao.jsp");
				rd.forward(request, response);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("clientes");
			dispatcher.forward(request, response);
		}
	}
}