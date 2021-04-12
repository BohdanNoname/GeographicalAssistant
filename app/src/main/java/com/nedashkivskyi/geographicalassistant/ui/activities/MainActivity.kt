package com.nedashkivskyi.geographicalassistant.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.coroutines.await
import com.nedashkivskyi.geographicalassistant.App
import com.nedashkivskyi.geographicalassistant.CountriesQuery
import com.nedashkivskyi.geographicalassistant.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun startCountryActivity(view: View){
        val textView = view.findViewById<TextView>(view.id)
        val intent = Intent(this, CountryActivity::class.java)
        intent.putExtra(App.ACTIVITY_INTENT_TAG, acronym(textView.text.toString()))
        startActivity(intent)
    }

    private fun acronym(nameCountry: String): String {
        val acronym = nameCountry.split(" ")
        return if (acronym.size > 1) {
            val arg1 = acronym[0][0]
            val arg2 = acronym[1][0]
            "$arg1$arg2".toUpperCase(Locale.ROOT)
        } else {
            val arg1 = nameCountry[0]
            val arg2 = nameCountry[1]
            "$arg1$arg2".toUpperCase(Locale.ROOT)
        }
    }
}