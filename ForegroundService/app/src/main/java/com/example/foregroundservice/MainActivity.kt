package com.example.foregroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener{
            val intent=Intent(this,ForegroundService::class.java)
                .apply {
                    action=ForegroundService.ACTION_START_FOREGROUND_SERVICE
                }
            startService(intent)
        }
        btnStop.setOnClickListener {
            val intent=Intent(this,ForegroundService::class.java)
                .apply {
                    action=ForegroundService.ACTION_STOP_FOREGROUND_SERVICE
                }
            stopService(intent)
        }
    }
}
