<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page isELIgnored="false" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UFT-8">
            <title>Cadastro de usuários</title>
            <link href="formCadastroUser.css" rel="stylesheet" type="text/css" />
        </head>

        <body>

            <form method="post" action="novoUsuario" class="formulario">
                <fieldset>
                    <legend>Cadastro do usuário</legend>

                    <label>E-mail
                        <input required id="email" type="email" name="email" placeholder="askopdasdkops@gmail.com" />
                    </label>

                    <label>Senha
                        <input required id="senha" type="password" name="senha" />
                    </label>

                    <label>CPF
                        <input required id="cpf" type="text" name="cpf" placeholder="XXXXXXXXX-XX" maxlength="11"
                            onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return true; else return false;">
                    </label>

                    <label>Nome
                        <input required id="nome" type="text" name="nome">
                    </label>

                    <label>Telefone
                        <input required id="telefone" type="tel" name="telefone" placeholder="(XX)XXXXX-XX"
                            maxlength="11"
                            onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return true; else return false;">
                    </label>

                    <label>Sexo
                        <select required id="sexo" name="sexo">
                            <option value="M">Masculino</option>
                            <option value="F">Feminino</option>
                        </select>
                    </label>

                    <label>Data de Nascimento
                        <input required id="nascimento" type="date" name="dataNascimento">
                    </label>

                    <button type="submit" id="botao">
                        Cadastrar
                </fieldset>
                </button>
            </form>
        </body>

        </html>