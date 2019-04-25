package com.example.foregroundservice

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat

private const val TAG = "ForegroundService"

class ForegroundService : Service() {
    companion object {
        const val ACTION_START_FOREGROUND_SERVICE = "start.foreground.service"
        const val ACTION_STOP_FOREGROUND_SERVICE = "stop.foreground.service"
        const val ACTION_PLAY = "action.play"
        const val ACTION_PAUSE = "action.pause"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "My foreground service onCreate()")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            ACTION_START_FOREGROUND_SERVICE -> {
                startForegroundService()
                Toast.makeText(this, "Foreground service start", Toast.LENGTH_SHORT).show()
            }
            ACTION_STOP_FOREGROUND_SERVICE -> {
                stopForegroundService()
                Toast.makeText(this, "Foreground service stop", Toast.LENGTH_SHORT).show()
            }
            ACTION_PLAY -> {
                Toast.makeText(this, "You click play button", Toast.LENGTH_SHORT).show()
            }
            ACTION_PAUSE -> {
                Toast.makeText(this, "You click pause button", Toast.LENGTH_SHORT).show()
            }
        }

        return START_NOT_STICKY
    }

    private fun startForegroundService() {
        Log.d(TAG, "Start foreground service")
        val input = "This is foreground service notification"
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        //add play button intent in notification
        val playIntent = Intent(this, ForegroundService::class.java)
            .apply {
                action = ACTION_PLAY
            }

        val playPendingIntent = PendingIntent.getService(this, 0, playIntent, 0)
         //add pause button intent in notification
        val pauseIntent = Intent(this, ForegroundService::class.java)
            .apply {
                action = ACTION_PAUSE
            }

        val pausePendingIntent = PendingIntent.getService(this, 0, pauseIntent, 0)


        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service Notification")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_play, "play", playPendingIntent)
            .addAction(R.drawable.ic_pause,"pause",pausePendingIntent)
            .build()
        startForeground(1, notification)

    }

    private fun stopForegroundService() {
        Log.d(TAG, "Stop foreground service")
        //Stop foreground service with stop notification
        stopForeground(true)
        //stop the foreground service
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
