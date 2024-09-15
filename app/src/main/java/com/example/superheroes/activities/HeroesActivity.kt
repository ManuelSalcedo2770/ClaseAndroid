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
import com.example.superheroes.activities.HeroesActivity
import com.example.superheroes.R
import com.example.superheroes.adapters.HeroAdapter
import com.example.superheroes.adapters.PublisherAdapter
import com.example.superheroes.models.Publisher
import com.example.superheroes.models.Studios
import com.example.superheroes.models.User
import com.squareup.picasso.Picasso

class HeroesActivity : AppCompatActivity() {
    lateinit var namePublisher : TextView
    lateinit var heroesRWshow : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_heroes)
        namePublisher = findViewById(R.id.publisher_title)
        heroesRWshow = findViewById(R.id.heroes_recycleview)
        val publisherId = intent.getIntExtra("publisherId",0)
        Log.i("HEROES ACTIVITY","Publisher ID: ${publisherId}")

        val publisher = Publisher.publishers.firstOrNull { publisher ->
            publisher.id == publisherId
        }
        Log.i("HEROES ACTIVITY",publisher.toString())
        namePublisher.text = publisher?.name

        val studios = Studios.studios.filter { studio ->
            studio.publisherId == publisherId
        }
        Log.i("HEROES ACTIVITY",studios.toString())
        heroesRWshow.adapter = HeroAdapter(studios){ studio->
            val intent = Intent(this@HeroesActivity,HeroDetailActivity::class.java)
            intent.putExtra("studioId",studio.id)
            startActivity(intent)
        }
        heroesRWshow.layoutManager = GridLayoutManager(this,1)
    }
}