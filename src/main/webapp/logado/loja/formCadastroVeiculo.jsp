<!DOCTYPE html>
<html>

<head>
    <meta charset="UFT-8">
    <title>Cadastro de veículos</title>
    <link rel="stylesheet" type="text/css" href="formCadastroVeiculo.css">
</head>

<body>
    <form class="formulario_de_loja">
        <fieldset>
            <legend>Cadastro de veículos</legend>

            <label>CNPJ
                <input id="cnpj" type="text" name="cnpj" placeholder="XX.XXX.XXX/0001-XX" maxlength="14"
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

            <label>Modelo
                <input id="modelo" type="text" name="modelo">
            </label>

            <label>Ano
                <input id="ano" type="number" name="ano" min="1950" max="2022" />
            </label>

            <label>Quilometragem
                <input id="quilometragem" type="text" name="quilometragem">
            </label>

            <label>
                <textarea id="descricao" name="descriao" rows="7" cols="40">Digite a descrição do veículo</textarea>
            </label>

            <label>Valor
                <input id="valor" type="text" name="valor" placeholder="20000,00"
                    onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode))) return true; else return false;">
            </label>

            <label>
                <input id="fotos" type="file" name="fotos" multiple accept="image/*">
            </label>

            <button method="post" action="admin/user/cadastro" id="botao">
                Cadastrar
        </fieldset>
        </button>
    </form>
</body>

</html>