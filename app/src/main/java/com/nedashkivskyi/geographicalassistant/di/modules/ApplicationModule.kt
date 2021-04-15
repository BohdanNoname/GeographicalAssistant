package com.nedashkivskyi.geographicalassistant.di.modules

import androidx.lifecycle.MutableLiveData
import com.nedashkivskyi.geographicalassistant.CountriesQuery
import com.nedashkivskyi.geographicalassistant.CountryQuery
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun providesCountriesQueryData(): MutableLiveData<CountriesQuery.Data?> {
        return MutableLiveData<CountriesQuery.Data?>()
    }

    @Provides
    fun providesCountryQueryData(): MutableLiveData<CountryQuery.Data?> {
        return MutableLiveData<CountryQuery.Data?>()
    }
}