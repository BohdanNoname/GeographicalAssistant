package com.nedashkivskyi.geographicalassistant.ui.fragments.data_countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nedashkivskyi.geographicalassistant.CountryQuery
import com.nedashkivskyi.geographicalassistant.R
import com.nedashkivskyi.geographicalassistant.databinding.FragmentDataCountryBinding
import com.nedashkivskyi.geographicalassistant.ui.SharedViewModel
import com.nedashkivskyi.geographicalassistant.ui.activities.CountryActivity
import com.squareup.picasso.Picasso
import java.io.File
import java.util.*
import javax.inject.Inject


class FragmentDataCountry: Fragment() {

    private lateinit var binding: FragmentDataCountryBinding
    @Inject lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        (activity as CountryActivity).countryComponent.inject(this)
        binding = FragmentDataCountryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(activity as CountryActivity).get(SharedViewModel::class.java)

        viewModel.country.observe(viewLifecycleOwner, Observer {
            uiRealization(it)
        })

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun uiRealization(it: CountryQuery.Data?){
        binding.viewModel = viewModel
        val country = viewModel._country.value?.country()!!
        val code = it?.country()?.code()!!
        binding.namePlusCode.text = country.name() + " - " + code

        val url = "https://maxmaracakes.com.ua/my_flags_images/${code.toLowerCase(Locale.ROOT)}.png"
        Picasso.get().load(url).into(binding.flag)

        val languages = arrayListOf<String>()
        for (language in country.languages()){
            languages.add(language.name().toString())
        }

        binding.languagesList.text = languages.toString().replace("[", "").replace("]", "")
    }
}