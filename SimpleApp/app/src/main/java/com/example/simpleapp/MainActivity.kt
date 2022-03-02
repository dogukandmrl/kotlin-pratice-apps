package com.example.simpleapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonHesapla.setOnClickListener {
            if(binding.editTextNumber.text.isNotEmpty()&&binding.editTextNumber2.text.isNotEmpty())
            {
                var sinav1 = binding.editTextNumber.text.toString().toInt()
                var sinav2 = binding.editTextNumber2.tag.toString().toInt()
                var ortalama: Double = (sinav1 + sinav2)/2.toDouble()
                if(ortalama>=50){
                    binding.textSonuc.text=ortalama.toString()+""+"Geçtiniz"
                }
                else
                {
                    binding.textSonuc.text=ortalama.toString()+""+"KALDINIZ"
                }
            }
            else{
                binding.textSonuc.text ="sınav notu giriniz."

            }
        }
    }
}




