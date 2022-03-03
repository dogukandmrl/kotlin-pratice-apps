package com.example.landmarkbookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.landmarkbookapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var landmarkList: ArrayList<Landmark>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Data
        landmarkList = ArrayList<Landmark>()
        val pisa = Landmark("Pisa","Italy",R.drawable.pisa)
        val rome = Landmark("Colosseum","Italy",R.drawable.rome)
        val london = Landmark("London Bridge","England",R.drawable.london)
        val eiffel = Landmark("Eiffel Tower","France",R.drawable.eiffel)
        landmarkList.add(pisa)
        landmarkList.add(rome)
        landmarkList.add(london)
        landmarkList.add(eiffel)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,landmarkList.map { landmark ->landmark.name})
        binding.listView.adapter = adapter
        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(MainActivity@this, DetailsActivity@this ::class.java)
            intent.putExtra("landmark",landmarkList.get(position))
            startActivity(intent)
        }

 
    }
}
