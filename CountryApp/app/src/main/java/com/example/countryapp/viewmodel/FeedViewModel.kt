package com.example.countryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryapp.model.Model

class FeedViewModel: ViewModel() {
    val countries = MutableLiveData<List<Model>>()
    val countryError = MutableLiveData<Boolean>()
    val countyLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Model("Turkey","Europe","Ankara","TRY","Turkish","www.ss.com")
        val country2 = Model("France","Europe","Paris","EUR","French","www.ss.com")
        val country3 = Model("Germany","Europe","Berlin","EUR","German","www.ss.com")

        val countryList = arrayListOf<Model>(country,country2,country3)
        countries.value = countryList
        countryError.value = false
        countyLoading.value = false

    }
}