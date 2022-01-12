package com.example.mvvmsample.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmsample.data.model.RoomModelTask
import androidx.lifecycle.LiveData




@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAllTasks(): List<RoomModelTask>

    @Insert
    fun insertTask(task: RoomModelTask)

    @Query("SELECT * FROM task WHERE id=:id")
    fun getTaskById(id: Int): RoomModelTask

    @Delete
    fun delete(task: RoomModelTask)

    @Query("SELECT COUNT(id) FROM task")
    fun getRowCount(): Int
}