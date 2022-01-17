package com.example.mvvmsample.domain.usecases

import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(private val tasksRepository: TasksRepository) {

    fun invoke(taskId: Int): Result<ModelTask> {
        val result = tasksRepository.getTaskById(taskId)
        return if (result.id != -1) {
            Result.success(result)
        } else {
            Result.failure(Throwable("Task not found"))
        }
    }
}