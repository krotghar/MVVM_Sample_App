package com.example.mvvmsample.app.di

import android.content.Context
import com.example.mvvmsample.app.presentation.view.FragmentTask
import com.example.mvvmsample.app.presentation.view.FragmentTaskList
import com.example.mvvmsample.app.presentation.view.createtask.FragmentCreateNewTask
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DomainModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: FragmentTaskList)
    fun inject(fragment: FragmentTask)
    fun inject(fragmentCreateNewTask: FragmentCreateNewTask)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}