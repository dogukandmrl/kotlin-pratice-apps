package com.example.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.countryapp.R
import com.example.countryapp.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_nation.*

class NationFragment : Fragment() {
    private lateinit var viewModel: CountryViewModel
    private var countryUuid = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nation, container, false)
    }
    fun OnViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        arguments?.let {
            countryUuid= NationFragmentArgs.fromBundle(it).countryUuid
        }
        observeLiveData()
    }
    private fun observeLiveData()
    {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            country->
            country?.let {
                countyname.text=country.CountryName
                countrycapital.text = country.CountryCapital
                countycurrency.text=country.countryCurrency
                countylanguage.text=country.countryLanguage
                countyRegion.text=country.countryRegion
            }
        })
    }



}