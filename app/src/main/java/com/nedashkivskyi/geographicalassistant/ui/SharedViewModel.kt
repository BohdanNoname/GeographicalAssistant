package com.nedashkivskyi.geographicalassistant.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nedashkivskyi.geographicalassistant.models.Country

class SharedViewModel: ViewModel() {
    val countryList = MutableLiveData<List<Country>>()

    fun foo(country: Country){

    }
}