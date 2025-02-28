<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Task;
use Illuminate\Http\Request;

class TaskController extends Controller
{
    //
    public function index()
    {
        $tasks = Task::all();

        if ($tasks->isEmpty()) {
            $data = [
                'ok' => false,
                'status' => 200,
                'data' => null,
                'message' => 'There is not tasks to show',
            ];

            return response()->json($data, 200);
        }

        $data = [
            'ok' => true,
            'status' => 200,
            'data' => $tasks,
        ];

        return response()->json($data, 200);
    }

    public function show($id)
    {
        $task = Task::findOrFail($id);

        if (!$task) {
            $data = [
                'ok' => false,
                'status' => 404,
                'message' => 'The user not found'
            ];

            return response()->json($data, 404);
        }

        $data = [
            'ok' => true,
            'status' => 200,
            'data' => $task
        ];

        return response()->json($data, 200);
    }

    public function store(Request $request)
    {
        $task = Task::create($request->all());

        if (!$task) {
            $data = [
                'ok' => false,
                'status' => 400,
                'message' => 'The data provided is not valid'
            ];

            return response()->json($data, 400);
        }

        $data = [
            'ok' => true,
            'status' => 201,
            'data' => $task
        ];

        return response()->json($data, 201);
    }

    public function update(Request $request, $id) {
        $task = Task::findOrFail($id);
        $task->update($request->all());

        $data = [
            'ok' => true,
            'status' => 200,
            'data' => $task
        ];

        return response()->json($data, 200);
    }

    public function destroy($id) {
        $task = Task::findOrFail($id);
        $task->delete();

        $data = [
            'ok' => true,
            'message' => 'Task deleted successfully'
        ];

        return response()->json($data, 204);
    }
}