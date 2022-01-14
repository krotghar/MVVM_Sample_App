package com.example.mvvmsample.domainTest.usecasesTest

import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository
import com.example.mvvmsample.domain.usecases.GetTaskByIdUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito

class TestGetTaskByIdUseCase {

    private val tasksRepository = Mockito.mock(TasksRepository::class.java)
    private val modelTaskErrorId = Mockito.mock(ModelTask::class.java)
    private val modelTaskCorrectId = Mockito.mock(ModelTask::class.java)

    @Before
    fun setUp() {
        Mockito.`when`(modelTaskErrorId.id).then { -1 }
        Mockito.`when`(modelTaskCorrectId.id).then { 1 }
    }

    @Test
    fun testWithModelTaskCorrectId() {
        Mockito.`when`(tasksRepository.getTaskById(anyInt())).then { modelTaskCorrectId }
        Assert.assertEquals(GetTaskByIdUseCase(tasksRepository).invoke(1).isSuccess, true)
    }

    @Test
    fun testWithModelTaskErrorId() {
        Mockito.`when`(tasksRepository.getTaskById(anyInt())).then { modelTaskErrorId }
        Assert.assertEquals(GetTaskByIdUseCase(tasksRepository).invoke(1).isFailure, true)
    }
}