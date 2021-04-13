package com.nedashkivskyi.geographicalassistant.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nedashkivskyi.geographicalassistant.CountriesQuery
import com.nedashkivskyi.geographicalassistant.CountryQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedViewModel @Inject constructor(): ViewModel() {
    private val _countriesList = MutableLiveData<CountriesQuery.Data?>()
    private val _country = MutableLiveData<CountryQuery.Data?>()

    val countryList: LiveData<CountriesQuery.Data?>
            get() = _countriesList

    val country: LiveData<CountryQuery.Data?>
            get() = _country

    fun setCountriesListResponse(countryList: CountriesQuery.Data?){
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                _countriesList.value = countryList
            }
        }
    }

    fun setCountryResponse(country: CountryQuery.Data?){
        GlobalScope.launch {
            withContext(Dispatchers.Main){
                _country.value = country
            }
        }
    }
}