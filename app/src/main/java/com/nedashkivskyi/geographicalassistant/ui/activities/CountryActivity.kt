package com.nedashkivskyi.geographicalassistant.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.nedashkivskyi.geographicalassistant.App
import com.nedashkivskyi.geographicalassistant.CountriesQuery
import com.nedashkivskyi.geographicalassistant.CountryQuery
import com.nedashkivskyi.geographicalassistant.R
import com.nedashkivskyi.geographicalassistant.databinding.ActivityCountryBinding
import com.nedashkivskyi.geographicalassistant.di.CountryActivityComponent
import com.nedashkivskyi.geographicalassistant.models.CountryList
import com.nedashkivskyi.geographicalassistant.ui.SharedViewModel
import com.nedashkivskyi.geographicalassistant.ui.fragments.data_countries.FragmentDataCountry
import com.nedashkivskyi.geographicalassistant.ui.fragments.list_countries.FragmentListCountries
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
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val continentAcronym:String = intent.getStringExtra(App.ACTIVITY_INTENT_TAG).toString()

        lifecycleScope.launchWhenCreated{
            val response = apolloClient.query(CountryQuery(continentAcronym)).await().data?.country()!!
//            val countryList: List<CountryQuery.Country> = listOf(response)
            viewModel = ViewModelProvider(this@CountryActivity).get(SharedViewModel::class.java)
            viewModel.selectCountry(response)

            supportFragmentManager.commit {
                replace<FragmentDataCountry>(R.id.country_data)
//                replace<FragmentListCountries>(R.id.countries_list)
                setReorderingAllowed(true)
                addToBackStack("")
            }
        }
    }
}