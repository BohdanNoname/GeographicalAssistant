package com.nedashkivskyi.geographicalassistant

import android.app.Application
import com.nedashkivskyi.geographicalassistant.di.DaggerApplicationComponent

class App: Application() {
        companion object{
                const val ACTIVITY_INTENT_TAG: String = "from MainActivity to CountryActivity"
        }
        val appComponent = DaggerApplicationComponent.create()!!
}