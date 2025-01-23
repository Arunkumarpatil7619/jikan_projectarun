package com.example.interview_app.Home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.interview_app.Home.data.Anime
import com.example.interview_app.R

class AnimeAdapter(
    private var animeList: List<Anime>,
    private val onItemClick: (Anime) -> Unit
) : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    // ViewHolder class to bind the views
    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animePoster: ImageView = itemView.findViewById(R.id.animePoster)
        val animeTitle: TextView = itemView.findViewById(R.id.animeTitle)
        val animeRating: TextView = itemView.findViewById(R.id.animeRating)
        val animeEpisodes: TextView = itemView.findViewById(R.id.animeEpisodes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]

        holder.animeTitle.text = anime.title
        holder.animeEpisodes.text = "Episodes: ${anime.episodes ?: "N/A"}"
        holder.animeRating.text = "Rating: ${anime.score ?: "N/A"}"
        Glide.with(holder.itemView.context)
            .load(anime.images.jpg.image_url)
            .into(holder.animePoster)

        holder.itemView.setOnClickListener {
            onItemClick(anime)
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    // Method to update the list dynamically
    fun submitList(newList: List<Anime>) {
        animeList = newList
        notifyDataSetChanged()
    }
}
