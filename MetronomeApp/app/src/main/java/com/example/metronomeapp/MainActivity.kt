package com.example.metronomeapp

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var buttonPlay :Button
    private lateinit var buttonStop: Button
    var mediaPlayer : MediaPlayer? = null
    var startPoint = 0
    var endPoint = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener

    {
        override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
            bpm.text = progress.toString() +""+ "BPM"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        if (seekBar != null)
        {
            startPoint = seekBar.progress
        }
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
        if(seekBar!= null)
        {
            endPoint = seekBar.progress
        }
            Toast.makeText(this@MainActivity,"changed by % +${endPoint-startPoint}",Toast.LENGTH_LONG)
        }


    })
        buttonPlay = findViewById(R.id.buttonPlay)
        buttonStop = findViewById(R.id.buttonStop)

        buttonPlay.setOnClickListener {
            if (seekBar.progress ==100)
            {
                playaudio(" https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3")
            }
            if (seekBar.progress==80)
            {
                playaudio("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-10.mp3")
            }
        }
        buttonStop.setOnClickListener {
            pauseaudio()
        }

}
    private fun playaudio(audioURL: String) {

        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            mediaPlayer!!.setDataSource(audioURL)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        }catch (e : IOException)
        {
            e.printStackTrace()
        }
        Toast.makeText(this,"BPM STARTED",Toast.LENGTH_LONG).show()
    }

    private fun pauseaudio() {
        if(mediaPlayer!!.isPlaying)
        {
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
        }else
        {
            Toast.makeText(this,"BPM STOPED",Toast.LENGTH_LONG).show()
        }
    }
}
