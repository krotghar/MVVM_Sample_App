package com.example.mvvmsample.domain.repository

import com.example.mvvmsample.domain.model.ModelTask
import io.reactivex.rxjava3.core.Single

interface TasksRepository {

    fun getTasks(): List<ModelTask>

    fun getTaskById(id: Int): ModelTask

    fun createTask(task: ModelTask): Boolean

    fun removeTask(task: ModelTask)

    fun getLastId(): Single<Int>

}