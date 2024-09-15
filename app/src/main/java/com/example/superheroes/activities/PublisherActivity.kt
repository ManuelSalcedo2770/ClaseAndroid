package com.example.superheroes.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.MainActivity
import com.example.superheroes.R
import com.example.superheroes.adapters.PublisherAdapter
import com.example.superheroes.models.Publisher
import com.example.superheroes.models.User

class PublisherActivity : AppCompatActivity() {
    lateinit var userTV : TextView
    lateinit var logoutBtn : ImageView
    lateinit var publisherRW : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_publisher)

        val sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE)
        userTV = findViewById(R.id.username)
        logoutBtn = findViewById(R.id.logout_btn)
        publisherRW = findViewById(R.id.publisher_recyclerview)

        val userEmail = sharedPreferences.getString("LOGGED_USER", null)

        val loggedUser = User.users.find { it.email == userEmail }
        if (loggedUser != null) {
            userTV.text = loggedUser.computedName
        } else {
            userTV.text = "Welcome back!"
        }

        publisherRW.adapter = PublisherAdapter(Publisher.publishers){ publisher->
            Log.i("Navigating to heroes for: ", publisher.name)
            val intent = Intent(this@PublisherActivity,HeroesActivity::class.java)
            intent.putExtra("publisherId",publisher.id)
            startActivity(intent)
        }
        publisherRW.layoutManager = GridLayoutManager(this,2)

        logoutBtn.setOnClickListener {
            Log.i("LOGOUT","Logging out of app")
            val editor = sharedPreferences.edit()
            editor.remove("isLogged")
            editor.apply()

            val intent = Intent(this@PublisherActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}