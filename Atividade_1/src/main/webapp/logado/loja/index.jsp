<%@ page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <!DOCTYPE html>
      <html>

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Menu do Sistema</title>
        <!-- <link rel="stylesheet" href="css/painelAdministrativo.css" type="text/css" /> -->
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

        <% String contextPath=request.getContextPath().replace("/", "" ); %>
          <h1>Página da loja</h1>

          <p>Olá ${sessionScope.usuarioLogado.nome}</p>

          <h4>
            <a href="${pageContext.request.contextPath}/loja/cadastrarVeiculo">
              Cadastrar veículos
            </a>
          </h4>

          <h4>
            <a href="${pageContext.request.contextPath}/logout"> Sair </a>
          </h4>

          <c:if test="${requestScope.listaPropostas.size() > 0}">
            <h3>Propostas vinculadas</h3>

            <table border="1">
              <c:forEach var="proposta" items="${requestScope.listaPropostas}">
                <tr>
                  <th>Status</th>
                  <th>Placa</th>
                  <th>Valor</th>
                  <th>Data</th>
                  <th>Resposta</th>
                </tr>
                <tr>
                  <td>${proposta.getStatus()}</td>
                  <td>${proposta.getVeicPlaca()}</td>
                  <td>${proposta.getValor()}</td>
                  <td>${proposta.getData()}</td>
                  <td>
                    <input type="submit" value="Aceitar">
                    <input type="submit" value="Recusar">
                  </td>
                </tr>
              </c:forEach>
            </table>
          </c:if>

          <c:if test="${requestScope.listaVeiculos.size() > 0}">
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
                    <div class="card-footer">
                      <a href="#" class="btn">Fazer proposta</a>
                    </div>
                  </div>
                </c:forEach>
              </c:if>
              <c:if test="${requestScope.listaVeiculos.size() == 0}">
                <h2> Não há veículos cadastrados na loja.</h2>
              </c:if>
            </div>

          </c:if>
      </body>

      </html>