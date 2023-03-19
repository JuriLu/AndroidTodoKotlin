package com.example.mytodolistproject

 import TaskItemAdapter
 import android.content.Intent
 import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import android.util.Log
 import android.widget.Button
 import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodolistproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),TaskItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels {
        TaskItemModelFactory((application as TodoApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newTaskButton.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        findViewById<Button>(R.id.goToLoginBtn)
            .setOnClickListener {
                Log.d("BUTTONS", "User tapped the Supabutton")
                val login = Intent(this, LoginActivity::class.java)
                startActivity(login)
            }
        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it,mainActivity)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem) {
        NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }
}