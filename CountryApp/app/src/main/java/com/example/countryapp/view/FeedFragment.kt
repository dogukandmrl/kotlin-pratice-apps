package com.example.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryapp.R
import com.example.countryapp.adapter.CountryAdapter
import com.example.countryapp.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*


class FeedFragment : Fragment() {
private lateinit var viewModel: FeedViewModel
private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_feed, container, false)
        observeLiveData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()
        countryList.layoutManager=LinearLayoutManager(context)
        countryList.adapter = countryAdapter
    }
   private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                countryList.visibility= View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it){
                    countryError.visibility=View.VISIBLE
                }else{
                    countryError.visibility=View.GONE
                }
            }
        })
        viewModel.countyLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it) {
                    countyLoading.visibility = View.VISIBLE
                    countryList.visibility = View.GONE
                    countryError.visibility = View.GONE
                } else{
                    countyLoading.visibility=View.GONE
                }
            }
        })
    }


}