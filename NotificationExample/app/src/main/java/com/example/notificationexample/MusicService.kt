package com.example.notificationexample

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import com.example.notificationexample.Constant.CHANNEL_ID
import com.example.notificationexample.Constant.MUSIC_NOTIFICATION_ID

class MusicService: Service() {
    private lateinit var musicPlayer : MediaPlayer
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showNotification()
        return START_STICKY
    }

    private fun showNotification() {
        val notificationIntent = Intent(this,MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)
        val notification = Notification
            .Builder(this, CHANNEL_ID)
            .setContentText("Metronom Player")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(MUSIC_NOTIFICATION_ID,notification)
    }

    override fun onCreate() {
        super.onCreate()
        initMusic()
        createNotificationChannel()
    }
    private fun createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "Music Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }
    private fun initMusic()
    {
    musicPlayer = MediaPlayer.create(this,R.raw.music_deneme)
    musicPlayer.isLooping = true
    musicPlayer.setVolume(100F,100F)
    }
}