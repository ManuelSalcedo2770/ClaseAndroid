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
import com.example.superheroes.adapters.PublisherAdapter
import com.example.superheroes.models.Publisher
import com.example.superheroes.models.User
import com.squareup.picasso.Picasso

class HeroesActivity : AppCompatActivity() {
    lateinit var publisherNameTV : TextView
    lateinit var heroesRecyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_heroes)
        publisherNameTV = findViewById(R.id.publisher_name)
        heroesRecyclerView = findViewById(R.id.heroes_recyclerview)
        val publisherId = intent.getIntExtra("publisherId",0)
        Log.i("Heroes Activity","User clicker on Publisher: ${publisherId}")

        val publisher = Publisher.publishers.firstOrNull { publisher ->
            publisher.id == publisherId
        }
        Log.i("Heroes Activity",publisher.toString())
        publisherNameTV.text = publisher?.name

        val heroes = Hero.heroes.filter { hero ->
            hero.publisherId == publisherId
        }
        Log.i("Heroes Activity",heroes.toString())
        heroesRecyclerView.adapter = HeroesAdapter(heroes){ hero->
            val intent = Intent(this@HeroesActivity,HeroDetailActivity::class.java)
            intent.putExtra("heroId",hero.id)
            startActivity(intent)
        }
        heroesRecyclerView.layoutManager = GridLayoutManager(this,2)
    }
}