package com.example.superheroes.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.superheroes.R
import com.example.superheroes.models.Studios
import com.squareup.picasso.Picasso

// La clase HeroDetailActivity define la pantalla de detalle de un héroe.
class HeroDetailActivity : AppCompatActivity() {

    // Declaración de variables para los elementos visuales de la interfaz.
    lateinit var nombreTV: TextView
    lateinit var descriptionTV: TextView
    lateinit var imageHero: ImageView

    // Método que se ejecuta al crear la pantalla.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita el diseño de borde a borde en la interfaz.
        enableEdgeToEdge()
        // Conecta la pantalla actual con el diseño de interfaz (activity_hero_detail.xml).
        setContentView(R.layout.activity_hero_detail)

        // Asigna a las variables los elementos visuales del diseño mediante su ID.
        imageHero = findViewById(R.id.hero_image)
        nombreTV = findViewById(R.id.hero_title)
        descriptionTV = findViewById(R.id.hero_description)

        // Obtiene el ID del estudio que fue enviado a esta pantalla.
        val studioId = intent.getIntExtra("studioId", 0)

        // Busca en la lista de estudios para encontrar el que tiene el mismo ID.
        val studio = Studios.studios.firstOrNull { studio: Studios ->
            studio.id == studioId
        }

        // Si se encuentra el estudio, se muestra su nombre y descripción en la pantalla.
        nombreTV.text = studio?.name
        descriptionTV.text = studio?.description

        // Usa Picasso (una herramienta para cargar imágenes) para mostrar la imagen del estudio.
        Picasso.get().load(studio?.image).into(imageHero)
    }
}