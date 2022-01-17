package com.example.mvvmsample.presentation.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.example.mvvmsample.data.repositoryImpl.TasksRepositoryImpl
import com.example.mvvmsample.data.storage.roomdatabase.Database
import com.example.mvvmsample.domain.repository.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataBindModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        context: Context
    ): Database {
        return databaseBuilder(
            context,
            Database::class.java,
            "task_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(db: Database) = db.taskDao()
}


@Module
interface DataBindModule {

    @Binds
    fun provideTasksRepositoryImplToTaskRepository(
        tasksRepositoryImpl: TasksRepositoryImpl
    ): TasksRepository



}