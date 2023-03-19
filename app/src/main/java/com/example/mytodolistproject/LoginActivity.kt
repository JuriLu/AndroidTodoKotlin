package com.example.mytodolistproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       val username = findViewById<TextView>(R.id.username)
       val password = findViewById<TextView>(R.id.password)



        findViewById<Button>(R.id.loginbtn)
            .setOnClickListener {
                if (username.text.toString().equals("admin") && password.text.toString().equals("admin")){
                    Log.d("Login Btn", "LOGIN SUCCESSFUL")
                    val mainAct = Intent(this, MainActivity::class.java)
                    startActivity(mainAct)
                } else {
                    Toast.makeText(getBaseContext(),"Login not successful",Toast.LENGTH_LONG).show();
                }

            }
    }
}