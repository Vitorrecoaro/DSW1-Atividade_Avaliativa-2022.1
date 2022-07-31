<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UFT-8">
                <title>Cadastro de usuários</title>
                <link href="formCadastroUser.css" rel="stylesheet" type="text/css" />
            </head>

            <body>

                <c:set var="usuario" value="${requestScope.usuario}" />
                <c:set var="pessoa" value="${requestScope.pessoa}" />

                <c:if test="${mensagens.existeErros}">
                    <div id="erro">
                        <ul>
                            <c:forEach var="erro" items="${mensagens.erros}">
                                <li> ${erro} </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <form method="post" action="atualizaUsuario?user_id=${usuario.getId()}" class="formulario">
                    <fieldset>
                        <legend>Edição de usuário</legend>

                        <label>E-mail
                            <input disabled id="email" type="email" name="email" placeholder="email@email.com"
                                value="${pessoa.getUsuario().getEmail()}" />
                        </label>

                        <label>Senha
                            <input required id="senha" type="password" name="senha"
                                value="${pessoa.getUsuario().getSenha()}" />
                        </label>

                        <label>CPF
                            <input required id="cpf" type="text" name="cpf" placeholder="XXXXXXXXX-XX" maxlength="11"
                                value="${pessoa.getCPF()}"
                                onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return true; else return false;">
                        </label>

                        <label>Nome
                            <input required id="nome" type="text" name="nome" value="${pessoa.getUsuario().getNome()}">
                        </label>

                        <label>Telefone
                            <input required id="telefone" type="tel" name="telefone" placeholder="(XX)XXXXX-XX"
                                maxlength="11" value="${pessoa.getTelefone()}"
                                onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return true; else return false;">
                        </label>

                        <label>Sexo
                            <select required id="sexo" name="sexo">
                                <option value="M">Masculino</option>
                                <option value="F">Feminino</option>
                            </select>
                        </label>

                        <label>Data de Nascimento
                            <input required id="nascimento" type="date" name="dataNascimento"
                                value="${pessoa.getDataNasc()}">
                        </label>

                        <label>
                            Tipo
                            <select required id="tipo" name="tipo" value="{pessoa.getTipo()}">
                                <option value="USER">Usuário</option>
                                <option value="ADMIN">Administrador</option>
                            </select>
                        </label>

                        <button type="submit" id="botao">
                            Editar
                    </fieldset>
                    </button>
                </form>
            </body>

            </html>