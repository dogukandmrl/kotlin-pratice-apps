package com.example.catchingkenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList



class MainActivity : AppCompatActivity() {
    var score =0
    var kennyArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable {  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //image array
        kennyArray.add(kenny1)
        kennyArray.add(kenny2)
        kennyArray.add(kenny3)
        kennyArray.add(kenny4)
        kennyArray.add(kenny5)
        kennyArray.add(kenny6)
        kennyArray.add(kenny7)
        kennyArray.add(kenny8)
        kennyArray.add(kenny9)

        hideImages()
        //countdowntimer

        object : CountDownTimer(15500,1000)
        {
            override fun onFinish() {
                timeScore.text= "Time: 0"
                handler.removeCallbacks(runnable)
                for (image in kennyArray)
                {
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart the game ?")
                alert.setPositiveButton("yes") {dialog, which ->
                val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("no"){dialog, which ->
                Toast.makeText(this@MainActivity,"game over",Toast.LENGTH_LONG).show()
                }
                alert.show()
            }
            override fun onTick(millisUntilFinished:Long) {
                timeScore.text ="Time:"+ millisUntilFinished/1000
            }

        }.start()
    }
    fun hideImages()
    {
        runnable = object : Runnable {
            override fun run() {
                for(image in kennyArray)
                {
                    image.visibility= View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(9)
                kennyArray[randomIndex].visibility = View.INVISIBLE
                handler.postDelayed(runnable,500)

            }

        }
        handler.post(runnable)
    }
    fun increaseScore(view: View)
    {
        score++
        scoreBoard.text ="Score = $score"
    }
}