package com.example.superheroes.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.superheroes.R
import com.example.superheroes.models.Publisher
import com.squareup.picasso.Picasso

class PublisherAdapter(
    val publishers: List<Publisher>,
    val onClick: (Publisher) -> Unit
) : RecyclerView.Adapter<PublisherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_publisher, parent, false)
        return PublisherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return publishers.count()
    }

    override fun onBindViewHolder(holder: PublisherViewHolder, position: Int) {
        val publisher = publishers[position]
        holder.nombrePublisher.text = publisher.name
        Picasso.get().load(publisher.image).into(holder.imgPublisher)

        holder.itemView.setOnClickListener {
            Log.i("PublisherAdapter", "Navigating to Publisher: ${publisher.name}")
            onClick(publisher)
        }
    }
}

class PublisherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nombrePublisher: TextView = view.findViewById(R.id.name_publishers)
    val imgPublisher: ImageView = view.findViewById(R.id.img_publisher_preview)
}
