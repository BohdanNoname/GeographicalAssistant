package com.nedashkivskyi.geographicalassistant.di.modules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nedashkivskyi.geographicalassistant.CountriesQuery
import com.nedashkivskyi.geographicalassistant.CountryQuery
import com.nedashkivskyi.geographicalassistant.models.Country
import com.nedashkivskyi.geographicalassistant.models.CountryList
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun providesViewModel(): MutableLiveData<CountriesQuery.Data?> {
        return MutableLiveData<CountriesQuery.Data?>()
    }
}