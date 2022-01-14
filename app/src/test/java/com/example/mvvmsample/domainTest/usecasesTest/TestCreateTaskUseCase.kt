package com.example.mvvmsample.domainTest.usecasesTest

import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository
import com.example.mvvmsample.domain.usecases.CreateTaskUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class TestCreateTaskUseCase {

    private val tasksRepository = Mockito.mock(TasksRepository::class.java)
    private val modelTaskEmptyName = Mockito.mock(ModelTask::class.java)
    private val modelTaskWithName = Mockito.mock(ModelTask::class.java)

    @Before
    fun setUp() {
        Mockito.`when`(modelTaskEmptyName.taskName).then { "" }
        Mockito.`when`(modelTaskWithName.taskName).then { "Test Task" }
    }

    @Test
    fun createTaskWithEmptyName() {
        Assert.assertEquals(CreateTaskUseCase(tasksRepository).invoke(modelTaskWithName), true)
        Assert.assertEquals(CreateTaskUseCase(tasksRepository).invoke(modelTaskEmptyName), false)

    }

}