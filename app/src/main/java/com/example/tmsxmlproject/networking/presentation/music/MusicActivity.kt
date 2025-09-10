package com.example.tmsxmlproject.networking.presentation.music

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.tmsxmlproject.databinding.ActivityMusincBinding

class MusicActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMusincBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMusincBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnStart.setOnClickListener {
            startForegroundService(Intent(this, MusicService::class.java))
        }

        viewBinding.btnStop.setOnClickListener {
            stopService(Intent(this, MusicService::class.java))
        }
    }
}