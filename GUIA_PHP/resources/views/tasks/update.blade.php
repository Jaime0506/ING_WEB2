@extends('layouts.app')

@section('content')
<div class="max-w-2xl w-full p-4 bg-white shadow-lg rounded-2xl flex gap-4">
    <!-- Sección de Listado de Tareas -->
    <div class="w-1/2">
        <h2 class="text-xl font-bold text-center mb-4">Lista de Tareas</h2>
        <div id="taskList" class="space-y-2">
            @foreach ($tasks as $task)
            <div class="p-3 border rounded-lg flex justify-between items-center bg-gray-100">
                <div>
                    <h3 class="font-semibold">{{ $task['name'] }}</h3>
                    <p class="text-sm text-gray-600">{{ $task['descripcion']}} </p>
                </div>
                @if ($task['completed'])
                <span class="px-2 py-1 text-xs font-semibold rounded-full bg-green-500 text-white">{{ $task['id'] }}</span>
                @else
                <span class="px-2 py-1 text-xs font-semibold rounded-full bg-red-500 text-white">{{ $task['id'] }}</span>
                @endif
            </div>
            @endforeach
        </div>
    </div>

    <!-- Sección para Actualizar Tarea -->
    <div class="w-1/2">
        <h2 class="text-xl font-bold text-center mb-4">Actualizar Tarea</h2>
        <form action="/app/tasks/update" method="POST">
            @csrf
            @method('PUT')
            <input type="number" name="id" id="updateTaskId" placeholder="ID de la tarea" class="w-full p-2 border rounded mb-2">
            <input type="text" name="name" id="updateTaskName" placeholder="Nuevo nombre" class="w-full p-2 border rounded mb-2">
            <input type="text" name="descripcion" id="updateTaskDescription" placeholder="Nueva descripción" class="w-full p-2 border rounded mb-2">
            <button type="submit" class="w-full bg-yellow-500 text-white p-2 rounded">Actualizar Tarea</button>
        </form>
    </div>
</div>
@endsection