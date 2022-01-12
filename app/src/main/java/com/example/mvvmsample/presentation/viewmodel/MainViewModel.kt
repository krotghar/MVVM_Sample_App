package com.example.mvvmsample.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.presentation.appcomponent.AppComponent
import com.example.mvvmsample.presentation.model.ModelTask
import com.example.mvvmsample.presentation.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val repository: TasksRepository) : ViewModel() {

    private var _tasksLiveData = MutableLiveData<List<ModelTask>>()
    val tasksLiveData get() = _tasksLiveData

    fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            _tasksLiveData.postValue(repository.getTasks())
        }
    }

    fun addTask(taskName: String, taskDescription: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(
                ModelTask(
                    repository.getLastId() + 1,
                    taskName,
                    taskDescription
                )
            )
        }
        getTasks()
    }

    fun removeTask(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val removedTask = repository.getTaskById(id)
            repository.removeTask(removedTask)
        }
        getTasks()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repo: TasksRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repo) as T
        }
    }
}