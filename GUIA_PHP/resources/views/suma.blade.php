<!-- Selecciono quien va a ser el layout padre -->
@extends('layouts.app')

<!-- Selecciono en que parte del template se deberia cargar, con el mismo nombre
    que se puso en el yiled del template
-->
@section('content')
<div class="border rounded flex flex-col gap-2 p-8">
    <h2>Suma de 2 numeros</h2>

    <form action="/suma" method="POST" class="flex flex-col">
        <!-- Encripta la informacion -->
        @csrf
        <label for="num1">Numero 1:</label>
        <input type="number" name="num1" id="num1" require class="rounded border">
        <br>

        <label for="num2">Numero 2: </label>
        <input type="number" name="num2" id="num2" require class="rounded border">
        <br>

        <button type="submit" class="bg-gray-400 p-2 rounded">Sumar</button>
    </form>

    <br>
    <!-- Verifica si resultado existe/ fue retornado por el back -->
    @if(isset($resultado))
    <h3>Resultado de la suma: {{ $resultado }}</h3>
    @endif
</div>
@endsection