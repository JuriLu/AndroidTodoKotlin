package com.example.mytodolistproject

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskViewModel(private val respository:TaskItemRepository): ViewModel() {
    var taskItems: LiveData<List<TaskItem>> = respository.allTaskItems.asLiveData()

    fun addTaskItem(newTask: TaskItem) = viewModelScope.launch {
        respository.insertTaskItem(newTask)
    }

    fun updateTaskItem(taskItem: TaskItem) = viewModelScope.launch{
        respository.update(taskItem)
    }

    fun setCompleted(taskItem: TaskItem) = viewModelScope.launch{
        if (!taskItem.isCompleted())
            taskItem.completedDateString = TaskItem.dateFormatter.format(LocalDate.now())
        respository.update(taskItem)
    }
}

class TaskItemModelFactory(private val respository:TaskItemRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(respository) as T

        throw java.lang.IllegalArgumentException("Unknown Class for View Model")
    }
}