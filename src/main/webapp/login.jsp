<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ page
isELIgnored="false" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Autenticação de Usuário</title>
            <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css" />
            <style type="text/css">
                legend {
                    width: 80%;
                    text-align: center;
                }

                .proposta_de_compra {
                    font-style: oblique;
                    font-size: 45px;
                    display: flex;
                    margin: 0 auto;
                    justify-content: center;
                    padding-top: 5%;
                }

                label {
                    display: block;
                    margin-top: 35px;
                }

                #enviar_proposta {
                    margin-top: 10%;
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
                            <li> ${erro} </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form  class="proposta_de_compra" method="post" action="index.jsp">
                <fieldset>
                    <legend>Autenticação do Usuário</legend>

                    <label>Login: 
                        <input name ="login" type="text" value="${param.login}"/>
                    </label>

                    <label>Senha: 
                        <input name="senha" type="password">
                    </label>

                    <button type="submit" id="enviar_proposta">
                        Entrar
                </fieldset>
                </button>
            </form>
        </body>
    </html>

