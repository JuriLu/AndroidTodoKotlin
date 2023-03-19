package com.example.mytodolistproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        findViewById<Button>(R.id.loginbtn)
            .setOnClickListener {
                Log.d("BUTTONS", "User tapped the Supabutton")
                val login = Intent(this, MainActivity::class.java)
                startActivity(login)
            }
    }
}