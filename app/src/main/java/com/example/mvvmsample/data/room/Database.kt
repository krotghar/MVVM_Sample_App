package com.example.mvvmsample.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvmsample.data.model.RoomModelTask

@Database(entities = [RoomModelTask::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}