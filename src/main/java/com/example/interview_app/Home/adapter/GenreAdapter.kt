package com.example.interview_app.Home.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.interview_app.Home.data.Genre
import com.example.interview_app.R

class GenreAdapter(private val genres: List<Genre>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreName: TextView = itemView.findViewById(R.id.genreName)
        val action: TextView = itemView.findViewById(R.id.action)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.genreName.text = genre.name
        holder.action.text = genre.action
        holder.itemView.setOnClickListener {
            val url = genre.url
            if (!url.isNullOrEmpty()) {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    holder.itemView.context.startActivity(intent)
                } catch (e: Exception) {
                    Log.e("GenreAdapter", "Error opening URL: ${e.message}")
                }
            } else {
                Log.e("GenreAdapter", "Invalid URL")
            }
        }
    }

    override fun getItemCount(): Int = genres.size
}
