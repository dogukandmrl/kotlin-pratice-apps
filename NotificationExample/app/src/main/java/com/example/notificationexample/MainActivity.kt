package com.example.notificationexample

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.notificationexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.btnForegroundService.setOnClickListener {
            startStopService()
        }
    }

    private fun startStopService() {
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
    private fun isMusicServiceRunning(mclass : Class<MusicService> ):Boolean{
val manager :ActivityManager = getSystemService(
    Context.ACTIVITY_SERVICE
)
        as ActivityManager
        for(service : ActivityManager.RunningServiceInfo in manager.getRunningServices(Integer.MAX_VALUE))
        {
        if (mclass.name.equals(service.service.className))
        {

        }
        }
        return false
    }
}