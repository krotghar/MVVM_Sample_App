package com.example.mvvmsample.data.storage.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmsample.data.storage.model.RoomModelTask

/**
 * Database абстрактный класс, благодаря которому Room любезно
 * сгенерирует для нас код для взаимодействия с БД
 *
 * @constructor Create empty Database
 */
@Database(entities = [RoomModelTask::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}