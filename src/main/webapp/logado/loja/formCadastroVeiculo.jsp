<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UFT-8">
                <title>Cadastro de veículos</title>
                <!-- <link rel="stylesheet" type="text/css" href="formCadastroVeiculo.css"> -->
                <style type="text/css">
                    legend {
                        width: 70%;
                        text-align: center;
                    }

                    .formulario_de_loja {
                        font-style: oblique;
                        font-size: 45px;
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

                <c:set var="loja" value="${requestScope.loja}" />
                <c:set var="usuario" value="${requestScope.usuario}" />

                <form class="formulario_de_loja" method="post" action="novoVeiculo">
                    <fieldset>
                        <legend>Cadastro de veículos</legend>

                        <label>CNPJ
                            <input disabled id="cnpj" type="text" name="cnpj" placeholder="XX.XXX.XXX/0001-XX"
                                maxlength="14" value="${loja.getCNPJ()}"
                                onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return true; else return false;">
                        </label>

                        <label>Placa
                            <input id="placa" type="text" name="placa" />
                        </label>

                        <label>Modelo
                            <input id="modelo" type="text" name="modelo">
                        </label>

                        <label>Chassi
                            <input id="chassi" type="text" name="chassi" />
                        </label>

                        <label>Ano
                            <input id="ano" type="number" name="ano" min="1950" max="2022" />
                        </label>

                        <label>Quilometragem
                            <input id="quilometragem" type="number" name="quilometragem">
                        </label>

                        <label>
                            <textarea id="descricao" name="descricao" rows="7" cols="40"
                                placeholder="Digite a descrição do veículo"></textarea>
                        </label>

                        <label>Valor
                            <input id="valor" type="number" name="valor" placeholder="20000,00"
                                onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return true; else return false;">
                        </label>

                        <label>
                            <input id="fotos" type="file" name="fotos" multiple accept="image/*">
                        </label>

                        <button type="submit" id="botao">
                            Cadastrar
                    </fieldset>
                    </button>
                </form>
            </body>

            </html>