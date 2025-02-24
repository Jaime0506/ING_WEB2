<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

use App\Http\Controllers\SumaController;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/inicio', function () {
    return view('inicio');
});

Route::get('/suma', [SumaController::class, 'index']);

// Sin controllador
// Para poder recibir los datos, pasados por un metodo post
// tengo que usar la Request
// Route::post('/suma', function (Request $request) {
//     // -> es la forma para acceder a un objeto y sus metodos / propiedades
//     // input() retorna todos los valores pasados x la peticion y los guarda en un arreglo
//     $result =  $request->input();

//     // Lo muestro en la aplicacion sin parar la ejecucion de mi codigo
//     // dump($result);

//     // Extraigo los campos pasados (el nombre va a ser el mismo al que le indiquemos en el atributo name de la vista.php)
//     $num1 = $request->input('num1');
//     $num2 = $request->input('num2');
//     $result_sum = $num1 + $num2;

//     // Se imprime literalmente en la pagina como texto plano, y vasta unicamente con llamar la variable
//     // para que php la concatene
//     return view('suma', ['resultado' => $result_sum]);
    
//     // Lo correcto seria tener toda esta logica dentro de un controllador
// });

// Con controllador: Automaticamente se pasan la Requests
Route::post('/suma', [SumaController::class, 'suma']);