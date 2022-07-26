<!DOCTYPE html>
<html>

<head>
    <meta charset="UFT-8">
    <title>Cadastro de lojas</title>
    <link rel="stylesheet" type="text/css" href="formCadastroLoja.css">
</head>

<body>

    <form class="formulario_de_loja">
        <fieldset>
            <legend>Cadastro de lojas</legend>

            <label>E-mail
                <input id="email" type="email" name="email" placeholder="askopdasdkops@gmail.com" />
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
                <textarea id="descricao" name="descriao" rows="7" cols="34">Digite a descrição da loja</textarea>
            </label>

            <button method="post" action="admin/user/cadastro" id="botao">
                Cadastrar
        </fieldset>
        </button>
    </form>
</body>

</html>