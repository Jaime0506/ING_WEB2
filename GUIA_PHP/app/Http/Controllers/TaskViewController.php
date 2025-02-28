<?php

namespace App\Http\Controllers;

use App\Models\Task;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class TaskViewController extends Controller
{
    //
    public function index()
    {
        $tasks = Task::all();

        return view('tasks.index', compact('tasks'));
    }

    public function createView()
    {
        return view('tasks.create');
    }

    public function create(Request $request)
    {
        $task = Task::create($request->all());

        return redirect('/app/tasks');
    }

    public function deleteView()
    {
        $tasks = Task::all();

        return view('tasks.delete', compact('tasks'));
    }

    public function delete(Request $request)
    {
        $task = Task::findOrFail($request->id);
        $task->delete();

        return redirect('/app/tasks');
    }

    public function updateView()
    {
        $tasks = Task::all();

        return view('tasks.update', compact('tasks'));
    }

    public function update(Request $request)
    {
        $task = Task::findOrFail($request->id);

        $data = array_filter($request->all(), function ($value) {
            return !is_null($value) && $value !== '';
        });

        unset($data['id']);

        $task->update($data);

        return redirect('/app/tasks');
    }
}
