package com.nedashkivskyi.geographicalassistant.models

import javax.inject.Inject
import javax.inject.Singleton


data class CountryList constructor(
    val name: String?,
    val code: String?,
    val capital: String?
)