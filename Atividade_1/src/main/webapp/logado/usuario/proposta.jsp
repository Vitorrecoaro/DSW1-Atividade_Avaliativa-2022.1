<!DOCTYPE html>
            <html>

            <head>
                <meta charset="UFT-8">
                <title>Proposta de Compra</title>
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
                        padding-top: 10%;
                    }

                    label {
                        display: block;
                        margin-top: 40px;
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

                <form  class="proposta_de_compra" method="post" action="novoUsuario">
                    <fieldset>
                        <legend>Proposta de Compra</legend>

                        <label>Valor da Proposta
                            <input required id="proposta" type="text" name="proposta" placeholder="10000,00"/>
                        </label>

                        <label>Condições de Pagamento
                            <select required id="condicoes" name="condicoes">
                                <option value="Vista">À vista</option>
                                <option value="Parcelado">Parcelado</option>
                            </select>
                        </label>


                        <button type="submit" id="enviar_proposta">
                            Enviar proposta
                    </fieldset>
                    </button>
                </form>
            </body>

            </html>