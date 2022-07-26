<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Menu do Sistema</title>
                <link rel="stylesheet" href="css/painelAdministrativo.css" type="text/css" />
            </head>

            <body>
                <% String contextPath=request.getContextPath().replace("/", "" ); %>
                    <h1>Página do Administrador</h1>

                    <p>Olá ${sessionScope.usuarioLogado.nome}</p>

                    <table>
                        <tr>
                            <th>
                                <a href="${pageContext.request.contextPath}/admin/cadastroUsuario">
                                    Cadastrar usuário
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/admin/cadastroLoja">
                                    Cadastrar loja
                                </a>
                            </th>
                            <th>
                                <a href="${pageContext.request.contextPath}/logout">Sair</a>
                            </th>
                        </tr>
                    </table>

                    <h3> Menu de cadastro </h3>
                    <div class="tables">
                        <c:if test="${requestScope.listaUsuarios.size() > 0}">
                            <h4>USUÁRIOS</h4>
                            <table>
                                <tr>
                                    <th>Nome</th>
                                    <th>Email</th>
                                    <th>Telefone</th>
                                    <th>Sexo</th>
                                    <th>Data de Nascimento</th>
                                    <th>Tipo usuário</th>
                                </tr>
                                <c:forEach var="usuario" items="${requestScope.listaUsuarios}">
                                    <tr>
                                        <td>${usuario.getNome()}</td>
                                        <td>${usuario.getEmail()}</td>
                                        <td>${usuario.getTelefone()}</td>
                                        <td>${usuario.getSexo()}</td>
                                        <td>${usuario.getDataNasc()}</td>
                                        <td>${usuario.getTipo()}</td>
                                        <td>
                                            <a href="/<%=contextPath%>/admin/atualizacao?user_id=${usuario.id}">
                                                Editar usuário</a>
                                        </td>
                                        <td>
                                            <a href="/<%=contextPath%>/admin/removeUsuario?user_id=${usuario.id}">
                                                Remover usuário
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>

                        <c:if test="${requestScope.listaLojas.size() > 0}">
                            <h4>LOJAS</h4>
                            <table>
                                <tr>
                                    <td>Nome</td>
                                    <td>Email</td>
                                    <td>Descrição</td>
                                </tr>
                                <c:forEach var="loja" items="${requestScope.listaLojas}">
                                    <tr>
                                        <td>${loja.getNome()}</td>
                                        <td>${loja.getEmail()}</td>
                                        <td>${loja.getDescricao()}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
            </body>

            </html>