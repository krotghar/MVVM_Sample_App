package com.example.mvvmsample.app.extension

import android.content.Context
import com.example.mvvmsample.app.app.TasksApp
import com.example.mvvmsample.app.di.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is TasksApp -> appComponent
        else -> applicationContext.appComponent
    }