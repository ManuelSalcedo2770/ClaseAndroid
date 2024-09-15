package com.example.superheroes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.R
import com.example.superheroes.models.Studios
import com.squareup.picasso.Picasso

// Adaptador para la lista de héroes, basado en un RecyclerView.
class HeroAdapter(
    // Lista de estudios que contienen los héroes.
    val studios: List<Studios>,
    // Función que se ejecuta cuando se hace clic en un héroe.
    val onClick: (Studios) -> Unit
) : RecyclerView.Adapter<HeroViewHolder>() {

    // Crea y devuelve un nuevo ViewHolder para mostrar un héroe en la lista.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        // Infla el diseño de cada héroe desde el archivo XML (item_hero.xml).
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
        return HeroViewHolder(view) // Devuelve el ViewHolder con la vista inflada.
    }

    // Devuelve el número total de héroes que hay en la lista.
    override fun getItemCount(): Int {
        return studios.count() // Usa la cantidad de estudios como el número de elementos.
    }

    // Enlaza los datos de un héroe con su ViewHolder para mostrar en la lista.
    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        // Obtiene el héroe (studio) en la posición actual.
        val studio = studios[position]

        // Asigna el nombre del héroe al TextView correspondiente.
        holder.nombreHero.text = studio.name

        // Carga la imagen del héroe usando Picasso y la muestra en el ImageView.
        Picasso.get().load(studio.image).into(holder.imageHero)

        // Establece una acción cuando se hace clic en el elemento (héroe).
        holder.itemView.setOnClickListener {
            onClick(studio) // Llama a la función que se pasa cuando se hace clic en el héroe.
        }
    }
}

// Clase que representa el ViewHolder para un héroe, con referencias a las vistas.
class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Referencia al TextView para mostrar el nombre del héroe.
    val nombreHero: TextView = view.findViewById(R.id.hero_name)

    // Referencia al ImageView para mostrar la imagen del héroe.
    val imageHero: ImageView = view.findViewById(R.id.img_hero_preview)
}
