package com.nedashkivskyi.geographicalassistant.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.nedashkivskyi.geographicalassistant.App
import com.nedashkivskyi.geographicalassistant.CountriesQuery
import com.nedashkivskyi.geographicalassistant.databinding.ActivityCountryBinding
import com.nedashkivskyi.geographicalassistant.di.CountryActivityComponent
import com.nedashkivskyi.geographicalassistant.models.CountryList
import javax.inject.Inject


class CountryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryBinding
    lateinit var countryComponent: CountryActivityComponent
    @Inject lateinit var apolloClient: ApolloClient
    @Inject lateinit var listCountry: CountryList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryComponent = (applicationContext as App).appComponent.countryComponent().create()
        countryComponent.inject(this)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryAcronym:String = intent.getStringExtra(App.ACTIVITY_INTENT_TAG).toString()

        lifecycleScope.launchWhenResumed{
            val response = apolloClient.query(CountriesQuery(countryAcronym)).await().data?.continent()?.countries()!!
            val size:Int = response.size
            
//            Добавить ViewModel для связки данных между фрагментами
        }
    }
}