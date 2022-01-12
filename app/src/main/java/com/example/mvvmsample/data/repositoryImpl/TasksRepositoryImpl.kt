package com.example.mvvmsample.data.repositoryImpl

import android.view.Display
import com.example.mvvmsample.data.model.RoomModelTask
import com.example.mvvmsample.data.room.Database
import com.example.mvvmsample.presentation.model.ModelTask
import com.example.mvvmsample.presentation.repository.TasksRepository

class TasksRepositoryImpl(private val database: Database) : TasksRepository {


    override fun getTasks(): List<ModelTask> {
        val tasks = database.taskDao().getAllTasks()
        return tasks.map {
            ModelTask(
                it.id,
                it.taskName,
                it.taskDescription
            )
        }
    }

    override fun getTaskById(id: Int): ModelTask {
       val task = database.taskDao().getTaskById(id)
        return ModelTask(
            task.id,
            task.taskName,
            task.taskDescription
        )
    }

    override fun addTask(task: ModelTask) {
        database.taskDao().insertTask(
            task = RoomModelTask(
                task.id,
                task.taskName,
                task.taskDescription
            )
        )
    }

    override fun removeTask(task: ModelTask) {
        database.taskDao().delete(
            task = RoomModelTask(
                task.id,
                task.taskName,
                task.taskDescription
            )
        )
    }

    override fun getLastId(): Int {
        return database.taskDao().getRowCount()
    }
}