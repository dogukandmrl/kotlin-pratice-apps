package com.example.countryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.model.Model
import com.example.countryapp.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_row.view.*

class CountryAdapter(val countryList: ArrayList<Model>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(var view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.name.text = countryList[position].CountryName
        holder.view.region.text = countryList[position].countryRegion
        holder.view.setOnClickListener {
            val action= FeedFragmentDirections.actionFeedFragmentToNationFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
    fun updateCountryList(newCountryList: List<Model>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}