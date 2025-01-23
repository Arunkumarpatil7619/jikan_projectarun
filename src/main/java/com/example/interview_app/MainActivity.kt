package com.example.interview_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.interview_app.Home.ui.Home_Activity
import com.example.interview_app.databinding.ActivityHomeBinding
import com.example.interview_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(applicationContext, Home_Activity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}