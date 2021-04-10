package com.nedashkivskyi.geographicalassistant.di

import com.nedashkivskyi.geographicalassistant.ui.activities.CountryActivity
import com.nedashkivskyi.geographicalassistant.ui.fragments.data_countries.FragmentDataCountry
import com.nedashkivskyi.geographicalassistant.ui.fragments.list_countries.FragmentListCountries
import dagger.Subcomponent

@Subcomponent
interface CountryActivityComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): CountryActivityComponent
    }

    fun inject(activity: CountryActivity)
    fun inject(fragment: FragmentDataCountry)
    fun inject(fragment: FragmentListCountries)
}