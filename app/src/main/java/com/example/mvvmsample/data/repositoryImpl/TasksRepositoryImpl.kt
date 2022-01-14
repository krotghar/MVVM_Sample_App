package com.example.mvvmsample.data.repositoryImpl

import com.example.mvvmsample.data.storage.model.RoomModelTask
import com.example.mvvmsample.data.storage.roomdatabase.Database
import com.example.mvvmsample.data.storage.roomdatabase.TaskDao
import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository

/**
 * Tasks repository impl
 *
 * @property storage передаем
 * @constructor Create empty Tasks repository impl
 */
class TasksRepositoryImpl(private val storage: TaskDao) : TasksRepository {


    override fun getTasks(): List<ModelTask> {
        val tasks = storage.getAllTasks()
        return tasks.map {
            createModelTaskFromRoomModelTask(it)
        }
    }

    override fun getTaskById(id: Int): ModelTask {
        val task = storage.getTaskById(id)
        return createModelTaskFromRoomModelTask(task)
    }

    override fun addTask(task: ModelTask) {
        storage.insertTask(
            createRoomModelTaskFromModelTask(task)
        )
    }

    override fun removeTask(task: ModelTask) {
        storage.delete(
            createRoomModelTaskFromModelTask(task)
        )
    }

    override fun getLastId(): Int {
        return storage.getRowCount()
    }

    override fun findTaskById(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * Create model task from room model task
     * @param roomModel - Модель используемая в нашем RoomStorage
     * @return возвращает модель Domain слоя [ModelTask]
     */
    private fun createModelTaskFromRoomModelTask(roomModel: RoomModelTask?): ModelTask {
        return ModelTask(
            id = roomModel?.id ?: -1,
            taskName = roomModel?.taskName ?: "",
            taskDescription = roomModel?.taskDescription ?: ""
        )
    }

    /**
     * Create room model task from model task
     * @param modelTask - модель Domain слоя
     * @return возвращает модель для Room [RoomModelTask]
     */
    private fun createRoomModelTaskFromModelTask(modelTask: ModelTask): RoomModelTask {
        return RoomModelTask(
            id = modelTask.id,
            taskName = modelTask.taskName,
            taskDescription = modelTask.taskDescription
        )
    }

}