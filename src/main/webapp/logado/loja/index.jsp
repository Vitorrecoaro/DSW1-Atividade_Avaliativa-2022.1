<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Menu do Sistema</title>
                <!-- <link rel="stylesheet" href="css/painelAdministrativo.css" type="text/css" /> -->
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

                <% String contextPath=request.getContextPath().replace("/", "" ); %>
                    <h1>Página da loja</h1>

                    <p>Olá ${sessionScope.usuarioLogado.nome}</p>

                    <h4>
                        <a href="${pageContext.request.contextPath}/loja/cadastrarVeiculo">
                            Cadastrar veículos
                        </a>
                    </h4>

                    <h4>
                        <a href="${pageContext.request.contextPath}/logout">
                            Sair
                        </a>
                    </h4>

                    <c:if test="${requestScope.listaPropostas.size() > 0}">
                        <h3>Propostas vinculadas</h3>

                        <c:forEach var="proposta" items="${requestScope.listaPropostas}">
                            Proposta
                        </c:forEach>

                    </c:if>
            </body>