<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

                <!DOCTYPE html>

                <html>

                <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                    <title>Veiculos</title>
                </head>
                <style type="text/css">
                    .card {

                        border: 2px solid #333;
                        border-radius: 2%;
                        background-color: #fffaf2;
                        padding: 10px 0px;
                        color: #112506;
                        width: 300px;

                    }

                    .card-header {

                        text-align: center;
                        color: #010101;

                    }

                    .card-header h3 {

                        border-bottom: 2px solid #333;

                    }

                    .card-body ul {

                        padding: 0;
                        margin: 0 10px;

                    }

                    .card-body li {

                        list-style: none;
                        padding: 20px 20px 0 20px;
                        font-family: Verdana, Geneva, Tahoma, sans-serif;
                        border-bottom: 2pxsolid#06d974;

                    }

                    .card-footer {

                        text-align: center;
                        padding: 15px 0;
                        margin-top: 20px;
                        margin-bottom: 10px;

                    }

                    .card-footer a {

                        text-decoration: none;
                        border: none;
                        background-color: #06d974;
                        color: #fff;
                        padding: 20px 30px;
                        font-weight: bold;
                        font-family: Verdana, Geneva, Tahoma, sans-serif;

                    }

                    .btn {
                        background-color: #06d974;
                        border: none;
                        color: white;
                        padding: 15px 32px;
                        text-align: center;
                        text-decoration: none;
                        display: inline-block;
                        font-size: 16px;
                    }

                    #body-content {
                        display: flex;
                        flex-direction: row;
                        flex-wrap: wrap;
                    }
                </style>

                <body>
                    <%String contextPath=request.getContextPath().replace("/", "" );%>
                        <c:if test="${mensagens.existeErros}">
                            <div id="erro">
                                <ul>
                                    <c:forEach var="erro" items="${mensagens.erros}">
                                        <li> ${erro} </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                        <h1 align="center"> Veiculos Disponíveis</h1>
                        <h4 align="center"><a href="${pageContext.request.contextPath}/login.jsp">Login</a></h4>
                        <br>
                        <br>
                        <div id="body-content">
                            <c:if test="${requestScope.listaVeiculos.size() > 0}">
                                <c:forEach var="veiculo" items="${requestScope.listaVeiculos}">
                                    <div class="card">

                                        <div class="card-header">

                                            <h2>Imagens do carro</h2>

                                            <h3>${veiculo.getModelo()}</h3>

                                        </div>
                                        <div class="card-body">

                                            <ul>

                                                <li><b>Placa:</b> ${veiculo.getPlaca()}</li>

                                                <li><b>Ano:</b> ${veiculo.getAno()}</li>

                                                <li><b>Quilometragem:</b> ${veiculo.getQuilometragem()}</li>

                                                <li><b>Descrição:</b> ${veiculo.getDescricao()}</li>

                                                <li><b>Loja:</b> ${veiculo.getCNPJ()}</li>

                                                <li><b>Valor:</b> R$ ${veiculo.getValor()}</li>

                                            </ul>
                                        </div>
                                        <c:if test="${requestScope.usuarioLogado != null}">
                                            <div class="card-footer">
                                                <a href="#" class="btn">Fazer proposta</a>
                                            </div>
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${requestScope.listaVeiculos.size() == 0}">
                                <h2> Não há veículos disponíveis.</h2>
                            </c:if>
                        </div>
                </body>

                </html>