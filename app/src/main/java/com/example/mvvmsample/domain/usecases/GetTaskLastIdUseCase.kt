package com.example.mvvmsample.domain.usecases

import com.example.mvvmsample.domain.repository.TasksRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * UseCase: вернуть последний максимальный id задачи
 *
 * @param tasksRepository - принимает имплементацию [TasksRepository]
 */
class GetTaskLastIdUseCase @Inject constructor(private val tasksRepository: TasksRepository) {

    fun invoke(): Single<Int> = tasksRepository.getLastId()
}