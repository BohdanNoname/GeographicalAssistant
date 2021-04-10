package com.nedashkivskyi.geographicalassistant.models

data class Country (
        val code: String?,
        val name: String?,
        val capital: String?,
        val native: String?,
        val currency: String?,
        val phone: String?,
        val emojiU: String?,
        val continentName: String?,
        val continentCode: String?,
        val languages: List<String>?,
        val states: List<String>?
        )