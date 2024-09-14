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
    lateinit var usernameTV : TextView
    lateinit var logoutBtn : ImageView
    lateinit var publishers_recyclerview : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_publisher)
        val sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE)
        usernameTV = findViewById(R.id.usernameTV)
        logoutBtn = findViewById(R.id.logoutBtn)
        publishers_recyclerview = findViewById(R.id.publishers_recyclerview)

        val userEmail = sharedPreferences.getString("LOGGED_USER", null)

        val loggedUser = User.users.find { it.email == userEmail }
        if (loggedUser != null) {
            usernameTV.text = loggedUser.computedName
        } else {
            usernameTV.text = "Hola"
        }

        publishers_recyclerview.adapter = PublisherAdapter(Publisher.publishers){ publisher->
            Log.i("The following publisher was clicked: ", publisher.name)
            val intent = Intent(this@PublisherActivity,HeroesActivity::class.java)
            intent.putExtra("publisherId",publisher.id)
            startActivity(intent)
        }
        publishers_recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        logoutBtn.setOnClickListener {
            Log.i("LOGOUT","Logging out... bye :)")
            val editor = sharedPreferences.edit()
            editor.remove("isLogged")
            editor.apply()

            val intent = Intent(this@PublisherActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}