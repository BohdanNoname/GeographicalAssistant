package com.nedashkivskyi.geographicalassistant

import android.app.Application
import android.content.Context
import com.nedashkivskyi.geographicalassistant.di.ApplicationComponent
import com.nedashkivskyi.geographicalassistant.di.DaggerApplicationComponent
import javax.inject.Inject

class App: Application() {
        companion object{
                const val ACTIVITY_INTENT_TAG: String = "from MainActivity to CountryActivity"
        }

        val appComponent = DaggerApplicationComponent.create()!!
}