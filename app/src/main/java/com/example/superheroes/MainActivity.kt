package com.example.superheroes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.R
import com.example.superheroes.activities.PublisherActivity
import com.example.superheroes.models.User
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var emailET: EditText
    lateinit var passwordET: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE)
        val isLogged = sharedPreferences.getBoolean("isLogged", false)

        if (isLogged) {
            val intent = Intent(this@MainActivity, PublisherActivity::class.java)
            startActivity(intent)
            finish()
        }

        Log.i("IS_LOGGED", isLogged.toString())

        emailET = findViewById(R.id.emailET)
        passwordET = findViewById(R.id.passwordET)
        loginBtn = findViewById(R.id.btnLogin)

        loginBtn.setOnClickListener { v ->
            Log.i("LOGGED_IN", "Logged in correctly")
            val email = emailET.text.toString()
            val password = passwordET.text.toString()

            Log.i("Email", email)
            Log.i("Password", password)

            if (email.isEmpty() || password.isEmpty()) {
                Log.i("LOGIN_ERROR", "Email Input is Empty")
                Snackbar.make(v, "Empty inputs, check again.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isValidUser = User.users.any { user ->
                user.email == email && user.password == password
            }

            if (!isValidUser) {
                Log.i("LOGIN_ERROR", "Incorrect input")
                Snackbar.make(v, "Invalid input for Email/Password", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.i("LOGIN_SUCCESSFUL", "Log In success")
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLogged", true)
            editor.apply()

            Snackbar.make(v, "Log In success", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, PublisherActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
