package com.example.mvvmsample.presentation.di

import android.content.Context
import com.example.mvvmsample.presentation.view.FragmentAddNewTask
import com.example.mvvmsample.presentation.view.FragmentTask
import com.example.mvvmsample.presentation.view.FragmentTaskList
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: FragmentTaskList)
    fun inject(fragment: FragmentTask)
    fun inject(fragmentAddNewTask: FragmentAddNewTask)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}