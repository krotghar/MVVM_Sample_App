package com.example.mvvmsample.presentation.appcomponent

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.mvvmsample.data.repositoryImpl.TasksRepositoryImpl
import com.example.mvvmsample.data.room.Database
import com.example.mvvmsample.presentation.repository.TasksRepository
import com.example.mvvmsample.presentation.viewmodel.MainViewModel

class AppComponent(context: Context) {
    private val taskRepo: TasksRepository

    init {
        val db = Room.databaseBuilder(
            context,
            Database::class.java,
            "task_db"
        ).build()

        taskRepo = TasksRepositoryImpl(db)
    }

    fun getMainViewModel(fragment: Fragment): MainViewModel {
        return ViewModelProvider(fragment, MainViewModel.Factory(taskRepo)).get(MainViewModel::class.java)
    }

}