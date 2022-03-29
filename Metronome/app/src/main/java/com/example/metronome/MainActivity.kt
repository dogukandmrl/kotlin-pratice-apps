package com.example.metronome

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
lateinit var runnable: Runnable
private var handler = Handler()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaPlayer = MediaPlayer.create(this,R.raw.song_example)

        seekbar.progress =0

        seekbar.max = mediaPlayer.duration

        btnPlay.setOnClickListener {

                mediaPlayer.start()
                btnPlay.setImageResource(R.drawable.ic_baseline_pause_24)
                startStopService()
            if (!mediaPlayer.isPlaying)
            {
                mediaPlayer.pause()
                btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, changed: Boolean) {
                if (changed)
                {
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        runnable = Runnable {
            seekbar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)
        mediaPlayer.setOnCompletionListener {
            btnPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekbar.progress=0
            
        }
    }
    private fun startStopService()
    {
        if (isMusicServiceRunning(MusicService::class.java))
        {
        Toast.makeText(this,"Service Stopped",Toast.LENGTH_SHORT).show()
            stopService(Intent(this,MusicService::class.java))
        }
        else{
            Toast.makeText(this,"Service Started",Toast.LENGTH_SHORT).show()
            startService(Intent(this,MusicService::class.java))
        }
    }
    private fun isMusicServiceRunning(mclass : Class<MusicService>):Boolean
    {
    val manager : ActivityManager= getSystemService(
        Context.ACTIVITY_SERVICE
    ) as ActivityManager
        for(service: ActivityManager.RunningServiceInfo in manager.getRunningServices((Integer.MAX_VALUE)))
        {
            if (mclass.name.equals(service.service.className))
            {
                return true
            }
        }
        return false
    }
}