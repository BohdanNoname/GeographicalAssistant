package com.nedashkivskyi.geographicalassistant.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.nedashkivskyi.geographicalassistant.App
import com.nedashkivskyi.geographicalassistant.CountriesQuery
import com.nedashkivskyi.geographicalassistant.R
import com.nedashkivskyi.geographicalassistant.databinding.ActivityCountryBinding
import com.nedashkivskyi.geographicalassistant.di.CountryActivityComponent
import com.nedashkivskyi.geographicalassistant.ui.SharedViewModel
import com.nedashkivskyi.geographicalassistant.ui.fragments.data_countries.FragmentDataCountry
import com.nedashkivskyi.geographicalassistant.ui.fragments.list_countries.FragmentListCountries
import java.io.IOException
import java.util.*
import javax.inject.Inject


class CountryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryBinding
    lateinit var countryComponent: CountryActivityComponent
    @Inject lateinit var apolloClient: ApolloClient
    @Inject lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryComponent = (applicationContext as App).appComponent.countryComponent().create()
        countryComponent.inject(this)
        val continentName:String = intent.getStringExtra(App.ACTIVITY_INTENT_TAG).toString()
        title = continentName

        binding = ActivityCountryBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@CountryActivity).get(SharedViewModel::class.java)
        lifecycleScope.launchWhenResumed {
            apolloClient.query(CountriesQuery(acronym(continentName))).enqueue(
                    object : ApolloCall.Callback<CountriesQuery.Data>(){
                        override fun onResponse(response: Response<CountriesQuery.Data>) {
                            try {
                                viewModel.setCountriesListResponse(response.data)
                                viewModel.setCountryResponse(null)
                                fragmentRealization()
                            } catch (e: IOException){
                                Log.d(" ", e.message.toString())
                            }
                        }
                        override fun onFailure(e: ApolloException) {
                            Log.d("onFailure", e.message, e)
                        }
                    }
            )
        }
    }

    private fun fragmentRealization(){
        try{
            supportFragmentManager.commit {
                replace<FragmentListCountries>(R.id.countries_list)
                replace<FragmentDataCountry>(R.id.country_data)
                setReorderingAllowed(true)
                addToBackStack("replaceFragmentList")
            }
            binding.progressBar.visibility = View.INVISIBLE
        } catch (e: IOException) {
            Log.d("IOException", e.message.toString())
        }
    }

    private fun acronym(nameContinent: String): String {
        val acronym = nameContinent.split(" ")
        return if (acronym.size > 1) {
            val arg1 = acronym[0][0]
            val arg2 = acronym[1][0]
            "$arg1$arg2".toUpperCase(Locale.ROOT)
        } else {
            val arg1 = nameContinent[0]
            val arg2 = nameContinent[1]
            "$arg1$arg2".toUpperCase(Locale.ROOT)
        }
    }
}