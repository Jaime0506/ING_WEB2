<?php

use App\Http\Controllers\Api\TaskController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

// Con el comando php artisan install:api no es necesario
// indicar api, en el path

// Route::apiResource('/tasks', TaskController::class);
Route::middleware('api')->group(function () {
    Route::apiResource('tasks', TaskController::class);
});

// Con apiResource, el automaticamente mapea las funciones con cada peticion, GET, POST, DELETE, etc
// de acuerdo a la siguiente tabla:

// GET    /api/tasks        -> index()
// POST   /api/tasks        -> store()
// GET    /api/tasks/{id}   -> show($id)
// PUT    /api/tasks/{id}   -> update(Request $request, $id)
// DELETE /api/tasks/{id}   -> destroy($id)