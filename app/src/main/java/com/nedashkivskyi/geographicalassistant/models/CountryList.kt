package com.nedashkivskyi.geographicalassistant.models

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryList @Inject constructor(){
    lateinit var listCountry: List<Country>
}