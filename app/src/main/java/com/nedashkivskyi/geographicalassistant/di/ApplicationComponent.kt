package com.nedashkivskyi.geographicalassistant.di

import com.nedashkivskyi.geographicalassistant.di.modules.ApplicationModule
import com.nedashkivskyi.geographicalassistant.di.modules.NetworkModule
import com.nedashkivskyi.geographicalassistant.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun countryComponent(): CountryActivityComponent.Factory
}