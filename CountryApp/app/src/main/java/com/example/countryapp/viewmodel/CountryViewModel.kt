package com.example.countryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.model.Model

class CountryViewModel: ViewModel() {
    val countryLiveData =MutableLiveData<Model>()

    fun getDataFromRoom()
    {
        val country = Model("Turkey","Europe","Ankara","TRY","Turkish","www.ss.com")
        countryLiveData.value=country

    }
}