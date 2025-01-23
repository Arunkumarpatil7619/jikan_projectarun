package com.example.interview_app.Home.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.interview_app.Home.adapter.GenreAdapter
import com.example.interview_app.Home.data.AnimeData
import com.example.interview_app.Home.viewmodel.AnimeViewModel
import com.example.interview_app.databinding.ActivityDetailsAnimeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsAnime : AppCompatActivity() {
    lateinit var binding: ActivityDetailsAnimeBinding
    private val viewModel_anime: AnimeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailsAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val malId = intent.getIntExtra("IdKey", -1)
        binding.progressBar.visibility=View.VISIBLE
        viewModel_anime.getAnimeDetails(malId)
        Log.d("kiccha", "onCreate: ")
        viewModel_anime.animeList_detail.observe(this, Observer { data ->
            binding.progressBar.visibility=View.GONE
            Log.d("MAX_SUDEEP", "onCreate: $data")
            updateUI(data?.data)
        })



    }
    private fun updateUI(data: AnimeData?) {
        if (data != null) {
            loadWebView(data.trailer?.url)
            val trailerUrl = data.trailer?.url

            if (!trailerUrl.isNullOrEmpty()) {

                binding.posterImage.visibility = View.GONE

            } else {

                binding.posterImage.visibility = View.VISIBLE
                Glide.with(this).load(data.images.jpg).into(binding.posterImage)

            }
            binding.synopsisTextView.text = data.synopsis
            binding.castTextView.text = "Main Cast: ${data.producers?.joinToString(", ") { it.name }}"
            binding.episodesTextView.text = "Episodes: ${data.episodes}"
            binding.ratingTextView.text = "Rating: ${data.score}"


            val genreAdapter = GenreAdapter(data.genres)
            binding.genreRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.genreRecyclerView.adapter = genreAdapter


        } else {
            Toast.makeText(this, "Data not available.", Toast.LENGTH_SHORT).show()
        }
    }

    fun loadWebView(url: String?) {
        if (url.isNullOrEmpty()) {
            Log.e("invalid url so existing from method", "Invalid URL")
            return
        }

        Log.e("lodingurl", "Loading WebView: $url")

        val embedUrl = url.replace("watch?v=", "embed/")

        val webSettings = binding.webview.settings

        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true

        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        val iframeHtml = """
        <html>
            <body style="margin:0;padding:0;">
                <iframe 
                    width="100%" 
                    height="100%" 
                    src="$embedUrl" 
                    frameborder="0" 
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                    allowfullscreen>
                </iframe>
            </body>
        </html>
    """.trimIndent()

        binding.webview.loadDataWithBaseURL(null, iframeHtml, "text/html", "utf-8", null)
           binding.webview.webChromeClient = WebChromeClient()



              binding.webview.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                Log.e("weberror", "WebView Error: $description")
            }


            override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
                Log.e("httperror", "HTTP Error: ${errorResponse?.reasonPhrase}")
            }





            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }


        }
    }

}