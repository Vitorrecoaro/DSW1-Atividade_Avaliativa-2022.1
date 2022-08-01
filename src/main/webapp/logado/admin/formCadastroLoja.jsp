<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UFT-8">
                <title>Cadastro de lojas</title>
                <style type="text/css">
                    legend {
                        width: 70%;
                        text-align: center;
                    }

                    .formulario_de_loja {
                        font-style: oblique;
                        font-size: 35px;
                        display: flex;
                        margin: 0 auto;
                        justify-content: center;
                        padding-top: 5%;
                    }

                    label {
                        display: block;
                        margin-bottom: 15px;
                    }

                    #botao {
                        margin-left: 35%;
                        font-size: 20px;
                    }
                </style>

            </head>

            <body>

                <c:if test="${mensagens.existeErros}">
                    <div id="erro">
                        <ul>
                            <c:forEach var="erro" items="${mensagens.erros}">
                                <li>${erro}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

                <form class="formulario_de_loja" method="post" action="novaLoja">
                    <fieldset>
                        <legend>Cadastro de lojas</legend>

                        <label>E-mail
                            <input id="email" type="email" name="email" placeholder="email@email.com" />
                        </label>

                        <label>Senha
                            <input id="senha" type="password" name="senha" />
                        </label>

                        <label>CNPJ
                            <input id="cnpj" type="text" name="cnpj" placeholder="XX.XXX.XXX/0001-XX" maxlength="14"
                                onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return true; else return false;">
                        </label>

                        <label>Nome
                            <input id="nome" type="text" name="nome">
                        </label>

                        <label>
                            <textarea id="descricao" name="descricao" rows="7" cols="34"
                                placeholder="Digite a descrição da loja"></textarea>
                        </label>

                        <button type="submit" id="botao">
                            Cadastrar
                    </fieldset>
                    </button>
                </form>
            </body>

            </html>