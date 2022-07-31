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
import br.ufscar.dc.dsw.dao.PessoaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Loja;
import br.ufscar.dc.dsw.domain.Pessoa;
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
		} else if (usuario.getTipo().equals("PESSOA")) {
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.getbyEmail(usuario.getEmail());
			if (pessoa.getTipo().equals("ADMIN")) {
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
							updatePessoa(request, response);
							break;
						case "/cadastroLoja":
							formCadastraLoja(request, response);
							break;
						case "/novaLoja":
							cadastraLoja(request, response);
							break;
						case "/removeLoja":
							removeLoja(request, response);
							break;
						case "/atualizaLoja":
							formAtualizacaoLoja(request, response);
							break;
						case "/atualizacaoLoja":
							updateLoja(request, response);
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
	}

	private void updateLoja(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Erro erros = new Erro();
		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("loja_id"));
		UsuarioDAO userDao = new UsuarioDAO();
		LojaDAO lojaDao = new LojaDAO();

		String cnpj = request.getParameter("cnpj");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String descricao = request.getParameter("descricao");
		Usuario usuario = userDao.getbyID(id);
		Usuario usuarioAtualizado = new Usuario(
				usuario.getId(), nome, senha, usuario.getEmail(), "LOJA");
		Loja loja = lojaDao.getbyEmail(usuario.getEmail());
		Loja lojaAtualizada = new Loja(loja.getId(), cnpj,
				usuarioAtualizado, descricao);
		try {
			userDao.update(usuarioAtualizado);
			lojaDao.update(lojaAtualizada);
		} catch (Exception e) {
			erros.add("Erro nos dados preenchidos.");
			request.setAttribute("mensagens", erros);
			request.setAttribute("usuario", usuario);
			request.setAttribute("pessoa", loja);
			request.setAttribute("user_id", id);
			RequestDispatcher rd = request.getRequestDispatcher(
					"/logado/admin/formEdicaoLoja.jsp");
			rd.forward(request, response);
		}
		response.sendRedirect("/leilao_veiculos/admin");
	}

	private void formAtualizacaoLoja(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("loja_id"));
		UsuarioDAO userDao = new UsuarioDAO();
		Usuario usuario = userDao.getbyID(id);
		LojaDAO dao = new LojaDAO();
		Loja loja = dao.getbyEmail(usuario.getEmail());
		request.setAttribute("usuario", usuario);
		request.setAttribute("loja", loja);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/logado/admin/formEdicaoLoja.jsp");
		dispatcher.forward(request, response);
	}

	private void removeLoja(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("loja_id"));
		LojaDAO lojaDAO = new LojaDAO();
		Loja loja = lojaDAO.getbyID(id);
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.getbyEmail(loja.getEmail());
		try {
			lojaDAO.delete(loja);
			dao.delete(usuario);
		} catch (Exception e) {
			Erro erros = new Erro();
			erros.add("Erro ao remover loja.");

			request.setAttribute("mensagens", erros);
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("admin");
		dispatcher.forward(request, response);
	}

	private void listaLojasUsuarios(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		LojaDAO daoLojas = new LojaDAO();
		PessoaDAO daoUsers = new PessoaDAO();
		List<Pessoa> listaPessoas = daoUsers.getAll();
		List<Loja> listaLojas = daoLojas.getAll();
		request.setAttribute("listaUsuarios", listaPessoas);
		request.setAttribute("listaLojas", listaLojas);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(
						"/logado/admin/painelAdministrativo.jsp");
		dispatcher.forward(request, response);
	}

	private void formCadastraLoja(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(
						"/logado/admin/formCadastroLoja.jsp");
		dispatcher.forward(request, response);
	}

	private void formCadastroUser(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(
						"/logado/admin/formCadastroUser.jsp");
		dispatcher.forward(request, response);
	}

	private void cadastraUser(HttpServletRequest request,
			HttpServletResponse response)
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
		Date dataNascimento = Date.valueOf(
				request.getParameter("dataNascimento"));
		try {
			Usuario usuario = new Usuario(nome, senha, email, "PESSOA");
			UsuarioDAO userDao = new UsuarioDAO();
			userDao.insert(usuario);
			Pessoa pessoa = new Pessoa(
					usuario, cpf, telefone, sexo, dataNascimento, tipo);
			PessoaDAO pessoaDao = new PessoaDAO();
			pessoaDao.insert(pessoa);
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

	private void cadastraLoja(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cnpj = request.getParameter("cnpj");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String descricao = request.getParameter("descricao");
		try {
			Usuario usuario = new Usuario(nome, senha, email, "LOJA");
			UsuarioDAO userDao = new UsuarioDAO();
			userDao.insert(usuario);
			Loja loja = new Loja(
					cnpj, usuario, descricao);
			LojaDAO lojaDao = new LojaDAO();
			lojaDao.insert(loja);
		} catch (Exception e) {
			Erro erros = new Erro();
			erros.add("Erro nos dados preenchidos.");

			request.setAttribute("mensagens", erros);

			RequestDispatcher rd = request
					.getRequestDispatcher("/admin/cadastroLoja");
			rd.forward(request, response);
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/admin");
		dispatcher.forward(request, response);
	}

	private void removeUser(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("user_id"));
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.getbyID(id);
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.getbyEmail(pessoa.getUsuario().getEmail());
		try {
			pessoaDAO.delete(pessoa);
			dao.delete(usuario);
		} catch (Exception e) {
			Erro erros = new Erro();
			erros.add("Erro ao remover usuário.");

			request.setAttribute("mensagens", erros);
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("admin");
		dispatcher.forward(request, response);

	}

	private void formAtualizacaoUser(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("user_id"));
		UsuarioDAO usuarioDao = new UsuarioDAO();
		Usuario usuario = usuarioDao.getbyID(id);
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoa = dao.getbyEmail(usuario.getEmail());
		request.setAttribute("usuario", usuario);
		request.setAttribute("pessoa", pessoa);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/logado/admin/formEdicaoUser.jsp");
		dispatcher.forward(request, response);
	}

	private void updatePessoa(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		Erro erros = new Erro();
		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("user_id"));
		UsuarioDAO userDao = new UsuarioDAO();
		PessoaDAO pessoaDao = new PessoaDAO();

		String CPF = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		char sexo = request.getParameter("sexo").charAt(0);
		String telefone = request.getParameter("telefone");
		String tipo = request.getParameter("tipo");
		Date dataNascimento = Date.valueOf(
				request.getParameter("dataNascimento"));
		Usuario usuario = userDao.getbyID(id);
		Usuario usuarioAtualizado = new Usuario(
				usuario.getId(), nome, senha, usuario.getEmail(), "PESSOA");
		Pessoa pessoa = pessoaDao.getbyEmail(usuario.getEmail());
		Pessoa pessoaAtualizada = new Pessoa(pessoa.getId(),
				usuarioAtualizado, CPF, telefone, sexo, dataNascimento, tipo);
		try {
			userDao.update(usuarioAtualizado);
			pessoaDao.update(pessoaAtualizada);
		} catch (Exception e) {
			erros.add("Erro nos dados preenchidos.");
			request.setAttribute("mensagens", erros);
			request.setAttribute("usuario", usuario);
			request.setAttribute("pessoa", pessoa);
			request.setAttribute("user_id", id);
			RequestDispatcher rd = request.getRequestDispatcher(
					"/logado/admin/formEdicaoUser.jsp");
			rd.forward(request, response);
		}
		response.sendRedirect("/leilao_veiculos/admin");
	}
}
