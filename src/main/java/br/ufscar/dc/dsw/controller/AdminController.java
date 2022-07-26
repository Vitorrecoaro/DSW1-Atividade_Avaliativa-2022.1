package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.LojaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Loja;
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
					case "/cadastroUsuario":
						formCadastroUser(request, response);
						break;
					case "/novoUsuario":
						cadastraUser(request, response);
						break;
					case "/removeUsuario":
						removeUser(request, response);
						break;
					case "/atualizacao":
						formAtualizacaoUser(request, response);
						break;
					case "/atualizaUsuario":
						updateUser(request, response);
						break;
					case "/cadastroLoja":
						formCadastraLoja(request, response);
						break;
					default:
						listaLojasUsuarios(request, response);
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

	private void listaLojasUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LojaDAO daoLojas = new LojaDAO();
		UsuarioDAO daoUsers = new UsuarioDAO();
		List<Usuario> listaUsuarios = daoUsers.getAll();
		List<Loja> listaLojas = daoLojas.getAll();
		request.setAttribute("listaUsuarios", listaUsuarios);
		request.setAttribute("listaLojas", listaLojas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/painelAdministrativo.jsp");
		dispatcher.forward(request, response);
	}

	private void formCadastraLoja(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formCadastroLoja.jsp");
		dispatcher.forward(request, response);
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
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		char sexo = request.getParameter("sexo").charAt(0);
		String telefone = request.getParameter("telefone");
		String tipo = request.getParameter("tipo");
		if (tipo == null || tipo.equals("")) {
			tipo = "USER";
		}
		Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));
		try {
			Usuario usuario = new Usuario(nome, senha, email, cpf,
					telefone, sexo, dataNascimento, tipo);
			UsuarioDAO dao = new UsuarioDAO();
			dao.insert(usuario);
		} catch (Exception e) {
			Erro erros = new Erro();
			erros.add("Erro nos dados preenchidos.");

			request.setAttribute("mensagens", erros);

			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/cadastroUsuario");
			rd.forward(request, response);
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/admin");
		dispatcher.forward(request, response);
	}

	private void removeUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("user_id"));
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.getbyID(id);
		dao.delete(usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin");
		dispatcher.forward(request, response);

	}

	private void formAtualizacaoUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("user_id"));
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.getbyID(id);
		request.setAttribute("usuario", usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formEdicaoUser.jsp");
		dispatcher.forward(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("user_id"));
		UsuarioDAO dao = new UsuarioDAO();

		String CPF = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		char sexo = request.getParameter("sexo").charAt(0);
		String telefone = request.getParameter("telefone");
		String tipo = request.getParameter("tipo");
		Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));
		Usuario usuario = dao.getbyID(id);
		Usuario usuarioAtualizado = new Usuario(usuario.getId(), nome, senha,
				email, CPF, telefone, sexo, dataNascimento, tipo);
		try {
			dao.update(usuarioAtualizado);
		} catch (Exception e) {
			erros.add("Erro nos dados preenchidos.");
			request.setAttribute("mensagens", erros);
			request.setAttribute("usuario", usuario);
			request.setAttribute("user_id", id);
			RequestDispatcher rd = request.getRequestDispatcher("/logado/admin/formEdicaoUser.jsp");
			rd.forward(request, response);
		}
		response.sendRedirect("/leilao_veiculos/admin");
		// RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
		// dispatcher.forward(request, response);
	}
}
