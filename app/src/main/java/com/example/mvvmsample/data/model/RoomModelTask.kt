package com.example.mvvmsample.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class RoomModelTask(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "task_name")
    val taskName: String,
    @ColumnInfo(name = "task_description")
    val taskDescription: String
)