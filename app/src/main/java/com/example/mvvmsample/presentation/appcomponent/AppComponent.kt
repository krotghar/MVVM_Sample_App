package com.example.mvvmsample.presentation.appcomponent

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.mvvmsample.data.repositoryImpl.TasksRepositoryImpl
import com.example.mvvmsample.data.storage.roomdatabase.Database
import com.example.mvvmsample.domain.repository.TasksRepository
import com.example.mvvmsample.presentation.viewmodel.MainViewModel

class AppComponent(context: Context) {
    private val taskRepo: TasksRepository

    init {
        val db = Room.databaseBuilder(
            context,
            Database::class.java,
            "task_db"
        ).build()

        taskRepo = TasksRepositoryImpl(db.taskDao())
    }

    fun getMainViewModel(fragment: Fragment): MainViewModel {
        return ViewModelProvider(fragment, MainViewModel.Factory(taskRepo))[MainViewModel::class.java]
    }

}