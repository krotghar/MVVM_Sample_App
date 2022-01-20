package com.example.mvvmsample.app.app

import android.app.Application
import com.example.mvvmsample.app.di.AppComponent
import com.example.mvvmsample.app.di.DaggerAppComponent

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