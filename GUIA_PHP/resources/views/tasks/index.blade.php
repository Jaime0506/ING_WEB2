@extends('layouts.app')

@section('content')
<div class="container">
    <div class="w-full p-4 shadow-lg rounded-2xl border ">
        <h2 class="text-xl font-bold text-center mb-4">Lista de Tareas</h2>
        <div class="space-y-2">
            @foreach ($tasks as $task)
            <div class="p-3 border rounded-lg flex justify-between items-center bg-gray-100">
                <div>
                    <h3 class="font-semibold">ID: {{$task['id'] }} {{ $task['name'] }}</h3>
                    <p class="text-sm text-gray-600">{{ $task['descripcion'] }}</p>
                </div>

                @if ($task['completed'])
                <span class="px-2 py-1 text-xs font-semibold rounded-full bg-green-500 text-white">Completado</span>
                @else
                <span class="px-2 py-1 text-xs font-semibold rounded-full bg-red-500 text-white">No Completado</span>
                @endif
            </div>
            @endforeach
        </div>
    </div>
</div>
@endsection