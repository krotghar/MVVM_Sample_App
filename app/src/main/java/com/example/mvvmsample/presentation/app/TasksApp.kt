package com.example.mvvmsample.presentation.app

import android.app.Application
import com.example.mvvmsample.presentation.di.AppComponent
import com.example.mvvmsample.presentation.di.DaggerAppComponent

class TasksApp : Application() {

    lateinit var appComponent: AppComponent
    private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }

}