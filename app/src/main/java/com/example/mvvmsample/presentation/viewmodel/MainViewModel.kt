package com.example.mvvmsample.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.presentation.appcomponent.AppComponent
import com.example.mvvmsample.domain.model.ModelTask
import com.example.mvvmsample.domain.repository.TasksRepository
import com.example.mvvmsample.domain.usecases.CreateTaskUseCase
import com.example.mvvmsample.domain.usecases.GetTasksUseCase
import com.example.mvvmsample.domain.usecases.RemoveTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(private val repository: TasksRepository) : ViewModel() {

    private var _tasksLiveData = MutableLiveData<List<ModelTask>>()
    val tasksLiveData get() = _tasksLiveData

    private var _addTaskStatus = MutableLiveData<Boolean>()
    val addTaskStatus get() = _addTaskStatus

    private var _removeTaskStatus = MutableLiveData<Boolean>()
    val removeTaskStatus get() = _removeTaskStatus


    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            _tasksLiveData.postValue(
                GetTasksUseCase(repository).invoke()
            )
        }
    }

    fun addTask(taskName: String, taskDescription: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _addTaskStatus.postValue(CreateTaskUseCase(repository).invoke(createModelTask(taskName, taskDescription)))
        }
        if (_addTaskStatus.value == true) {
            getTasks()
        }
    }
    
// TODO: переписать

//    fun removeTask(id: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            RemoveTaskUseCase(repository).invoke()
//        }
//        getTasks()
//    }

    private fun createModelTask(
        taskName: String = "",
        taskDescription: String = ""
    ) = ModelTask(
        repository.getLastId() + 1,
        taskName,
        taskDescription
    )

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repo: TasksRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repo) as T
        }
    }
}