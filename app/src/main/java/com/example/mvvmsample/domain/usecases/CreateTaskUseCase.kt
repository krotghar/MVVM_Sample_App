package com.example.mvvmsample.domain.usecases

import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(private val tasksRepository: TasksRepository) {

    fun invoke(task: ModelTask): Boolean {
        return if (task.taskName != "") {
            tasksRepository.addTask(task)
            true
        } else {
            false
        }
    }
}