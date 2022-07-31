<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UFT-8">
                <title>Cadastro de lojas</title>
                <link rel="stylesheet" type="text/css" href="formCadastroLoja.css">
            </head>

            <body>

                <c:set var="loja" value="${requestScope.loja}" />
                <c:set var="usuario" value="${requestScope.usuario}" />

                <c:if test="${mensagens.existeErros}">
                    <div id="erro">
                        <ul>
                            <c:forEach var="erro" items="${mensagens.erros}">
                                <li> ${erro} </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <form class="formulario_de_loja" method="post" action="atualizacaoLoja?loja_id=${usuario.getId()}">
                    <fieldset>
                        <legend>Edição de lojas</legend>

                        <label>E-mail
                            <input disabled id="email" type="email" name="email" placeholder="email@email.com"
                                value="${loja.getEmail()}" />
                        </label>

                        <label>Senha
                            <input id="senha" type="password" name="senha" value="${loja.getSenha()}" />
                        </label>

                        <label>CNPJ
                            <input id="cnpj" type="text" name="cnpj" placeholder="XX.XXX.XXX/0001-XX" maxlength="14"
                                value="${loja.getCNPJ()}"
                                onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return true; else return false;">
                        </label>

                        <label>Nome
                            <input id="nome" type="text" name="nome" value="${loja.getNome()}">
                        </label>

                        <label>
                            <textarea id="descricao" name="descricao" rows="7" cols="34"
                                placeholder="Digite a descrição da loja" value="${loja.getDescricao()}">
                            </textarea>
                        </label>

                        <button type="submit" id="botao">
                            Editar
                    </fieldset>
                    </button>
                </form>
            </body>

            </html>