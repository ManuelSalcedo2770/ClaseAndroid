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

// La clase PublisherActivity define una pantalla que muestra una lista de publicadores
// y permite al usuario navegar a los héroes de cada publicador.
class PublisherActivity : AppCompatActivity() {

    // Declaración de variables para mostrar el nombre de usuario, el botón de logout y la lista de publicadores.
    lateinit var userTV : TextView
    lateinit var logoutBtn : ImageView
    lateinit var publisherRW : RecyclerView

    // Método que se ejecuta cuando se crea la pantalla.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita el diseño de borde a borde.
        enableEdgeToEdge()
        // Asocia esta clase con su diseño (activity_publisher.xml).
        setContentView(R.layout.activity_publisher)

        // Obtiene las preferencias compartidas del dispositivo (para datos guardados, como la sesión del usuario).
        val sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE)

        // Asocia las variables con los elementos visuales correspondientes en el diseño.
        userTV = findViewById(R.id.username)
        logoutBtn = findViewById(R.id.logout_btn)
        publisherRW = findViewById(R.id.publisher_recyclerview)

        // Obtiene el correo del usuario que está actualmente logueado.
        val userEmail = sharedPreferences.getString("LOGGED_USER", null)

        // Busca al usuario logueado en la lista de usuarios guardados.
        val loggedUser = User.users.find { it.email == userEmail }

        // Si encuentra al usuario logueado, muestra su nombre en la pantalla.
        if (loggedUser != null) {
            userTV.text = loggedUser.computedName
        } else {
            // Si no se encuentra, muestra un mensaje genérico.
            userTV.text = "Welcome back!"
        }

        // Configura el adaptador para mostrar la lista de publicadores.
        publisherRW.adapter = PublisherAdapter(Publisher.publishers) { publisher ->
            // Al hacer clic en un publicador, muestra sus héroes en una nueva pantalla.
            Log.i("Navigating to heroes for: ", publisher.name)
            val intent = Intent(this@PublisherActivity, HeroesActivity::class.java)
            intent.putExtra("publisherId", publisher.id)
            startActivity(intent) // Inicia la actividad para mostrar los héroes del publicador seleccionado.
        }

        // Define cómo se organizan los elementos en la lista, en este caso en 2 columnas.
        publisherRW.layoutManager = GridLayoutManager(this, 2)

        // Configura la acción para el botón de logout.
        logoutBtn.setOnClickListener {
            Log.i("LOGOUT", "Logging out of app")
            // Elimina la sesión guardada en las preferencias compartidas.
            val editor = sharedPreferences.edit()
            editor.remove("isLogged")
            editor.apply()

            // Vuelve a la pantalla principal (MainActivity) y cierra esta pantalla.
            val intent = Intent(this@PublisherActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Cierra la actividad actual para que el usuario no pueda volver atrás.
        }
    }
}