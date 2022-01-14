package com.example.mvvmsample.domain.usecases

import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository

class GetTasksUseCase(private val tasksRepository: TasksRepository) {

    fun invoke(): List<ModelTask> {
        return tasksRepository.getTasks()
    }
}