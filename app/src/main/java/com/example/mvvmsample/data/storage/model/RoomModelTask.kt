package com.example.mvvmsample.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room model task - модель, которая описывает сущность, хранимую в БД
 *
 * @property id id записи в БД
 * @property taskName имя задачи
 * @property taskDescription описание задачи
 */

@Entity(tableName = "task")
data class RoomModelTask(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "task_name")
    val taskName: String,
    @ColumnInfo(name = "task_description")
    val taskDescription: String
)