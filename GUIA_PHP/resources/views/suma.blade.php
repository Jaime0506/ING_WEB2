<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <h2>Suma de 2 numeros</h2>

    <form action="/suma" method="POST">
        <!-- Encripta la informacion -->
        @csrf
        <label for="num1">Numero 1:</label>
        <input type="number" name="num1" id="num1" require>
        <br>

        <label for="num2">Numero 2: </label>
        <input type="number" name="num2" id="num2" require>
        <br>

        <button type="submit">Sumar</button>
    </form>

    <br>

    <!-- Verifica si resultado existe/ fue retornado por el back -->
    @if(isset($resultado))
        <h3>Resultado de la suma: {{ $resultado }}</h3>
    @endif

</body>

</html>