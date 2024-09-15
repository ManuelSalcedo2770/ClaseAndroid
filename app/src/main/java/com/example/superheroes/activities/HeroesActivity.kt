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
import com.example.superheroes.R
import com.example.superheroes.adapters.HeroAdapter
import com.example.superheroes.models.Publisher
import com.example.superheroes.models.Studios
import com.squareup.picasso.Picasso

// La clase HeroesActivity define una pantalla que muestra los héroes de un publicador específico.
class HeroesActivity : AppCompatActivity() {

    // Declaración de variables para el título del publicador y la lista de héroes.
    lateinit var namePublisher : TextView
    lateinit var heroesRWshow : RecyclerView

    // Método que se ejecuta cuando se crea la pantalla.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita el diseño de borde a borde.
        enableEdgeToEdge()
        // Asocia esta clase con su diseño (activity_heroes.xml).
        setContentView(R.layout.activity_heroes)

        // Conecta las variables a los elementos visuales mediante su ID.
        namePublisher = findViewById(R.id.publisher_title)
        heroesRWshow = findViewById(R.id.heroes_recycleview)

        // Obtiene el ID del publicador que se envió a esta pantalla.
        val publisherId = intent.getIntExtra("publisherId", 0)
        Log.i("HEROES ACTIVITY", "Publisher ID: ${publisherId}") // Muestra el ID del publicador en el log.

        // Busca el publicador correspondiente al ID recibido.
        val publisher = Publisher.publishers.firstOrNull { publisher ->
            publisher.id == publisherId
        }
        Log.i("HEROES ACTIVITY", publisher.toString()) // Muestra los detalles del publicador en el log.

        // Muestra el nombre del publicador en la pantalla.
        namePublisher.text = publisher?.name

        // Filtra los estudios que pertenecen a ese publicador.
        val studios = Studios.studios.filter { studio ->
            studio.publisherId == publisherId
        }
        Log.i("HEROES ACTIVITY", studios.toString()) // Muestra los estudios encontrados en el log.

        // Configura el adaptador que maneja la lista de héroes en la pantalla.
        heroesRWshow.adapter = HeroAdapter(studios) { studio ->
            // Al hacer clic en un héroe, se abre una nueva pantalla con los detalles del héroe.
            val intent = Intent(this@HeroesActivity, HeroDetailActivity::class.java)
            intent.putExtra("studioId", studio.id)
            startActivity(intent) // Inicia la actividad de detalle del héroe.
        }

        // Define cómo se organizan los elementos en la lista, en este caso en una sola columna.
        heroesRWshow.layoutManager = GridLayoutManager(this, 1)
    }
}