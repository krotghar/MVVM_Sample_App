package com.example.mvvmsample.app.di

import com.example.mvvmsample.domain.repository.TasksRepository
import com.example.mvvmsample.domain.usecases.CreateTaskUseCase
import com.example.mvvmsample.domain.usecases.GetTaskByIdUseCase
import com.example.mvvmsample.domain.usecases.GetTasksUseCase
import com.example.mvvmsample.domain.usecases.RemoveTaskUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideCreateTaskUseCase(tasksRepository: TasksRepository) =
        CreateTaskUseCase(tasksRepository)

    @Provides
    fun provideGetTaskByIdUseCase(tasksRepository: TasksRepository) =
        GetTaskByIdUseCase(tasksRepository)

    @Provides
    fun provideGetTasksUseCase(tasksRepository: TasksRepository) =
        GetTasksUseCase(tasksRepository)

    @Provides
    fun provideRemoveTaskUseCase(
        tasksRepository: TasksRepository,
        getTaskByIdUseCase: GetTaskByIdUseCase
    ) = RemoveTaskUseCase(tasksRepository, getTaskByIdUseCase)

}