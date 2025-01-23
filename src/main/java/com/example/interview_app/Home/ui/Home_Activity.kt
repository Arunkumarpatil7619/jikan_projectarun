package com.example.interview_app.Home.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interview_app.Home.adapter.AnimeAdapter
import com.example.interview_app.Home.data.Anime
import com.example.interview_app.Home.viewmodel.AnimeViewModel
import com.example.interview_app.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: AnimeViewModel by viewModels()
    private lateinit var adapter: AnimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager =GridLayoutManager(this,2)

        adapter = AnimeAdapter(emptyList()) { anime ->
            val intent = Intent(applicationContext, DetailsAnime::class.java)
            intent.putExtra("IdKey", anime.mal_id)
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
        binding.progressBar.visibility= View.VISIBLE
          viewModel.fetchTopAnime()
        viewModel.animeList.observe(this, Observer { data ->
            if(data!=null)
                binding.progressBar.visibility= View.GONE
                Log.e("MAX_Sudeep", "onCreate: "+data )
               adapter.submitList(data)

        })
    }
}
