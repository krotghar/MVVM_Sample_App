package com.example.mvvmsample

import android.app.Application
import com.example.mvvmsample.presentation.appcomponent.AppComponent

class TasksApp : Application() {
    val appComponent: AppComponent by lazy { AppComponent(this) }
}