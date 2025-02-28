@extends('layouts.app')

@section('content')
<div class="container">
    <div class="w-full p-4 bg-white shadow-lg rounded-2xl">
        <h2 class="text-xl font-bold text-center mb-4">Agregar Nueva Tarea</h2>

        <form action="/app/tasks/create" method="POST">
            @csrf
            <input type="text" name="name" placeholder="Nombre de la tarea" class="w-full p-2 border rounded mb-2">
            <input type="text" name="descripcion" placeholder="Descripción" class="w-full p-2 border rounded mb-2">
            <button type="submit" class="w-full bg-blue-500 text-white p-2 rounded">Agregar Tarea</button>
        </form>
    </div>
</div>
@endsection