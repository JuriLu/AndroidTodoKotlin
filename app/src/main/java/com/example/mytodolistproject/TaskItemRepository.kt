package com.example.mytodolistproject

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TaskItemRepository(private val taskItemDao: TaskItemDao) {

    val allTaskItems: Flow<TaskItem> = taskItemDao.allTaskItems()

    @WorkerThread
    suspend fun insertTaskItem(taskItem: TaskItem){
        taskItemDao.insertTaskItem(taskItem)
    }

    @WorkerThread
    suspend fun update(taskItem: TaskItem){
        taskItemDao.updateTaskItem(taskItem)
    }
}