package com.example.landmarkbookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.landmarkbookapp.databinding.ActivityDetailsBinding

private lateinit var binding: ActivityDetailsBinding
class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent

        val selectedLandmark = intent.getSerializableExtra("landmark") as Landmark

        binding.textName.text = selectedLandmark.name
        binding.textCountry.text = selectedLandmark.country
        binding.imageView.setImageResource(selectedLandmark.image)

    }
}