package com.example.mvvmsample.presentation.extension

import android.content.Context
import com.example.mvvmsample.presentation.app.TasksApp
import com.example.mvvmsample.presentation.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is TasksApp -> appComponent
        else -> applicationContext.appComponent
    }