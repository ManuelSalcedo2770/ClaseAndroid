package com.example.superheroes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.superheroes.R
import com.example.superheroes.models.Studios
import com.squareup.picasso.Picasso

class HeroAdapter(
    val studios: List<Studios>,
    val onClick: (Studios) -> Unit
) : RecyclerView.Adapter<HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
        return HeroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studios.count()
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val studio = studios[position]
        holder.nombreHero.text = studio.name
        Picasso.get().load(studio.image).into(holder.imageHero)

        holder.itemView.setOnClickListener {
            onClick(studio)
        }
    }
}

class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nombreHero: TextView = view.findViewById(R.id.hero_name)
    val imageHero: ImageView = view.findViewById(R.id.img_hero_preview)
}
