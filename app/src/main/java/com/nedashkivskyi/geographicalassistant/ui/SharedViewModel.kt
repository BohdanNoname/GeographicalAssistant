package com.nedashkivskyi.geographicalassistant.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nedashkivskyi.geographicalassistant.CountryQuery
import com.nedashkivskyi.geographicalassistant.models.CountryList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedViewModel @Inject constructor(): ViewModel() {
    /*Share List<CountryList>*/
    @Inject lateinit var mutableSelectedCountryList: MutableLiveData<CountryQuery.Country>
    val selectedCountry: LiveData<CountryQuery.Country> get() = mutableSelectedCountryList

    fun selectCountry(countryList: CountryQuery.Country){
        mutableSelectedCountryList.value = countryList
    }
}