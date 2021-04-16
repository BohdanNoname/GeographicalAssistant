package com.nedashkivskyi.geographicalassistant.ui.fragments.data_countries

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nedashkivskyi.geographicalassistant.databinding.FragmentDataCountryBinding
import com.nedashkivskyi.geographicalassistant.ui.SharedViewModel
import com.nedashkivskyi.geographicalassistant.ui.activities.CountryActivity
import com.squareup.picasso.Picasso
import java.util.*
import javax.inject.Inject


class FragmentDataCountry: Fragment() {

    private lateinit var binding: FragmentDataCountryBinding
    @Inject lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        (activity as CountryActivity).countryComponent.inject(this)
        binding = FragmentDataCountryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(activity as CountryActivity).get(SharedViewModel::class.java)

        viewModel.country.observe(viewLifecycleOwner, {
            if (it != null){
                uiRealization()
            }
        })

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun uiRealization() {
        binding.viewModel = viewModel
        visibilityUiComponents()
        val country = viewModel._country.value?.country()!!
        val code = country.code()

        binding.namePlusCode.text = country.name() + " - " + code

//        Getting images from my own server by unique acronym of every country
        val url = "https://maxmaracakes.com.ua/my_flags_images/${code.toLowerCase(Locale.ROOT)}.png"
        Picasso.get().load(url).into(binding.flag)

        val languages = arrayListOf<String>()
        for (language in country.languages()){
            languages.add(language.name().toString())
        }
        binding.languagesList.text = languages.toString().replace("[", "").replace("]", "")

        binding.phoneCode.text = "+" + country.phone()
    }

    private fun visibilityUiComponents(){
        binding.languagesRow.visibility = View.VISIBLE
        binding.nativeRow.visibility = View.VISIBLE
        binding.currencyRow.visibility = View.VISIBLE
        binding.phoneRow.visibility = View.VISIBLE
        binding.helpsText.visibility = View.INVISIBLE
    }
}