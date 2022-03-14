package com.example.metronomeapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat



class MainActivity : AppCompatActivity() {
    private lateinit var buttonPlay :Button
    private lateinit var buttonStop: Button
    var mediaPlayer : MediaPlayer? = null
    var startPoint = 0
    var endPoint = 0
    val CHANNEL_ID ="channelId"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("BPM NOTIFICATION")
            .setContentText("You can go bpm app ")
            .setSmallIcon(R.drawable.notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()
        val NotificationManager = NotificationManagerCompat.from(this)
        notificationButton.setOnClickListener {
            NotificationManager.notify(NOTIFICATION_ID,notification)
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.bpm_sound)
        mediaPlayer?.setOnPreparedListener { println("calis") }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                bpm.text = progress.toString() + "" + "BPM"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPoint = seekBar.progress
                }
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                if (seekBar != null) {
                    endPoint = seekBar.progress
                }
                Toast.makeText(this@MainActivity,
                    "changed by % +${endPoint - startPoint}",
                    Toast.LENGTH_LONG)
            }
        })
    }
    override fun onResume() {
        super.onResume()
        buttonPlay = findViewById(R.id.buttonPlay)
        buttonStop = findViewById(R.id.buttonStop)
        buttonPlay.setOnClickListener {
            if (seekBar.progress == 100) {
                playaudio()
            }
            if (seekBar.progress == 80) {
                playaudio()
            }
        }
        buttonStop.setOnClickListener {
            pauseaudio()
        }
    }
    private fun playaudio() {
        var resId = resources.getIdentifier(R.raw.bpm_sound.toString(),"raw",packageName)
        val mediaPlayer = MediaPlayer.create(this,resId)
        mediaPlayer.start()
        Toast.makeText(this,"BPM STARTED",Toast.LENGTH_LONG).show()
    }
    private fun pauseaudio() {
        if(mediaPlayer!!.isPlaying)
        {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer!!.reset()
        }else
        {
            Toast.makeText(this,"BPM STOPED",Toast.LENGTH_LONG).show()
        }
    }
    fun createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                    lightColor = Color.GREEN
                enableLights(true)

            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}
