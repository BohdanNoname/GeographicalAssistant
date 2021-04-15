package com.nedashkivskyi.geographicalassistant.ui.fragments.data_countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nedashkivskyi.geographicalassistant.databinding.FragmentDataCountryBinding
import com.nedashkivskyi.geographicalassistant.ui.SharedViewModel
import com.nedashkivskyi.geographicalassistant.ui.activities.CountryActivity
import javax.inject.Inject


class FragmentDataCountry: Fragment() {

    private lateinit var binding: FragmentDataCountryBinding
    @Inject lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        (activity as CountryActivity).countryComponent.inject(this)
        binding = FragmentDataCountryBinding
            .inflate(inflater, container, false)
        viewModel = ViewModelProvider(activity as CountryActivity)
            .get(SharedViewModel::class.java)

        viewModel.country.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
//            (binding.emoji as ImageView).load(File("src/main/res/drawable/flags/ad.png"))
        })
        return binding.root
    }
}