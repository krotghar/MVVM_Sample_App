package com.example.mvvmsample.presentation.repository

import com.example.mvvmsample.presentation.model.ModelTask

interface TasksRepository {

    fun getTasks(): List<ModelTask>

    fun getTaskById(id: Int): ModelTask

    fun addTask(task: ModelTask)

    fun removeTask(task: ModelTask)

    fun getLastId(): Int
}