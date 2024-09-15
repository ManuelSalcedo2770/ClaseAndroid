package com.example.superheroes.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.superheroes.R
import com.example.superheroes.models.Studios
import com.squareup.picasso.Picasso

class HeroDetailActivity : AppCompatActivity() {
    lateinit var nombreTV: TextView
    lateinit var descriptionTV: TextView
    lateinit var imageHero: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hero_detail)
        imageHero = findViewById(R.id.hero_image)
        nombreTV = findViewById(R.id.hero_title)
        descriptionTV = findViewById(R.id.hero_description)

        val studioId = intent.getIntExtra("studioId", 0)
        val studio = Studios.studios.firstOrNull { studio: Studios ->
            studio.id == studioId
        }

        nombreTV.text = studio?.name
        descriptionTV.text = studio?.description
        Picasso.get().load(studio?.image).into(imageHero)
    }
}