package com.example.mvvmsample.data.repositoryImpl

import com.example.mvvmsample.data.storage.model.RoomModelTask
import com.example.mvvmsample.data.storage.roomdatabase.TaskDao
import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository
import javax.inject.Inject

/**
 * Иплементация интерфейса репозитория
 * @property storage интерфейс для работы с хранилищем данных
 */
class TasksRepositoryImpl @Inject constructor(private val storage: TaskDao) : TasksRepository {

    /**
     * Функция получения списка всех задач из Storage
     * @return лист [ModelTask]
     */
    override fun getTasks(): List<ModelTask> {
        val tasks = storage.getAllTasks()
        return tasks.map {
            createModelTaskFromRoomModelTask(it)
        }
    }

    /**
     * Получение конкретной задачи по ее id
     * @param id id задачи
     * @return возвращает [ModelTask]
     */
    override fun getTaskById(id: Int): ModelTask {
        val task = storage.getTaskById(id)
        return createModelTaskFromRoomModelTask(task)
    }

    /**
     * Добавление задачи в хранилище
     * @param task - задача [ModelTask]
     */
    override fun addTask(task: ModelTask) {
        storage.insertTask(
            createRoomModelTaskFromModelTask(task)
        )
    }

    /**
     * Удаление задачи из хранилища
     * @param task задача которую необходимо удалить
     */
    override fun removeTask(task: ModelTask) {
        storage.delete(
            createRoomModelTaskFromModelTask(task)
        )
    }

    /**
     * Получение последнего id задачи
     * @return возвращает id последней задачи в БД
     */
    override fun getLastId(): Int {
        return storage.getRowCount()
    }



    /**
     * Маппер моделей из Data слоя в Domain слой
     * @param roomModel - Модель [RoomModelTask] используемая в нашем RoomStorage
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
     * Маппер моделей из Domain слоя в Data слой
     * @param modelTask - модель Domain слоя [ModelTask]
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