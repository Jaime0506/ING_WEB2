<?php

namespace App\Http\Controllers;

use App\Models\Task;
use Illuminate\Http\Request;

class TasksController extends Controller
{
    //
    public function index()
    {
        return Task::all();
    }

    public function store(Request $request)
    {
        // $task =  Task:
    }

    // Forma sin mapeo de laravel
    public function getTasks()
    {
        $tasks = Task::all();

        return $tasks;
    }
}
