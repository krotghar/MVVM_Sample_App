package com.example.mvvmsample.domain.usecases

import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository

class RemoveTaskUseCase(private val tasksRepository: TasksRepository) {

    fun invoke(task: ModelTask): Boolean {
        if (GetTaskByIdUseCase(tasksRepository).invoke(task.id).isFailure)
            return false

        tasksRepository.removeTask(task)
        return true
    }
}