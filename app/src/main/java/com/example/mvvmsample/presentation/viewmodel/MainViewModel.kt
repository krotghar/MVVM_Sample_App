package com.example.mvvmsample.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository
import com.example.mvvmsample.domain.usecases.CreateTaskUseCase
import com.example.mvvmsample.domain.usecases.GetTaskByIdUseCase
import com.example.mvvmsample.domain.usecases.GetTasksUseCase
import com.example.mvvmsample.domain.usecases.RemoveTaskUseCase
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: TasksRepository,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val removeTaskUseCase: RemoveTaskUseCase
    ) : ViewModel() {

    private var _tasksLiveData = MutableLiveData<List<ModelTask>>()
    val tasksLiveData get() = _tasksLiveData

    /**
     * @property _currentTask приватное поле livedata, которая хранит текущий таск
     * @property currentTask публичное поле для подписки слушателя
     */


    private var _addTaskStatus = MutableLiveData<Boolean>()
    val addTaskStatus get() = _addTaskStatus

    private var _removeTaskStatus = MutableLiveData<Boolean>()
    val removeTaskStatus get() = _removeTaskStatus


    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            _tasksLiveData.postValue(
                getTasksUseCase.invoke()
            )
        }
    }

    fun addTask(taskName: String, taskDescription: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _addTaskStatus.postValue(createTaskUseCase.invoke(createModelTask(taskName, taskDescription)))
        }
        if (_addTaskStatus.value == true) {
            getTasks()
        }
    }


    /**
     * Функция вызывает [RemoveTaskUseCase] для удаления таска
     *
     */
    fun removeTask(task: ModelTask) {
        viewModelScope.launch(Dispatchers.IO) {
            _removeTaskStatus.postValue(removeTaskUseCase.invoke(task))
            if (_removeTaskStatus.value == true)
                getTasks()
        }
    }

    private fun createModelTask(
        taskName: String = "",
        taskDescription: String = ""
    ) = ModelTask(
        repository.getLastId() + 1,
        taskName,
        taskDescription
    )

    @Suppress("UNCHECKED_CAST")
    class MainViewModelFactory @AssistedInject constructor(
        private val repo: TasksRepository,
        private val getTaskByIdUseCase: GetTaskByIdUseCase,
        private val createTaskUseCase: CreateTaskUseCase,
        private val getTasksUseCase: GetTasksUseCase,
        private val removeTaskUseCase: RemoveTaskUseCase
        ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                repository = repo,
                getTaskByIdUseCase = getTaskByIdUseCase,
                createTaskUseCase = createTaskUseCase,
                getTasksUseCase = getTasksUseCase,
                removeTaskUseCase = removeTaskUseCase
            ) as T
        }

        @AssistedFactory
        interface Factory {

            fun create(): MainViewModelFactory

        }
     }
}