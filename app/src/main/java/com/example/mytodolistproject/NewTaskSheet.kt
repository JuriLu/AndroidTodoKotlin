package com.example.mytodolistproject

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mytodolistproject.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if(taskItem != null){
            binding.taskTitle.text = "Edit Task"
            val editable = Editable.Factory.getInstance()

            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.name.text = editable.newEditable(taskItem!!.desc)
        }
        else {
            binding.taskTitle.text = "New Task"
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener{
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container, false)
        return binding.root }

    private fun saveAction()
    {
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()

        if (taskItem == null){
            val newTask = TaskItem(name,desc,null,null)
            taskViewModel.addTaskItem(newTask)
        } else {
            taskViewModel.updateTaskItem(
                taskItem!!.id,
                name,
                desc,
                null
            )

        }

        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }
}