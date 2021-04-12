package com.nedashkivskyi.geographicalassistant.ui.fragments.data_countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.of
import androidx.lifecycle.ViewModelStores.of
import com.nedashkivskyi.geographicalassistant.databinding.FragmentDataCountryBinding
import com.nedashkivskyi.geographicalassistant.ui.SharedViewModel
import com.nedashkivskyi.geographicalassistant.ui.activities.CountryActivity
import java.util.EnumSet.of
import javax.inject.Inject

class FragmentDataCountry : Fragment() {

    private lateinit var binding: FragmentDataCountryBinding
    @Inject lateinit var viewModel: SharedViewModel

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProviders.of(activity as CountryActivity).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as CountryActivity).countryComponent.inject(this)
        binding = FragmentDataCountryBinding.inflate(inflater, container, false)
        viewModel.selectedCountry.observe(viewLifecycleOwner, Observer { countryList ->
            binding.textView.text = countryList.capital()
        })
        return binding.root
    }
}