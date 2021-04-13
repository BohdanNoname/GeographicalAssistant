package com.nedashkivskyi.geographicalassistant.ui.fragments.data_countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nedashkivskyi.geographicalassistant.databinding.FragmentDataCountryBinding
import com.nedashkivskyi.geographicalassistant.ui.SharedViewModel
import com.nedashkivskyi.geographicalassistant.ui.activities.CountryActivity
import javax.inject.Inject

class FragmentDataCountry : Fragment() {

    private lateinit var binding: FragmentDataCountryBinding
    @Inject lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as CountryActivity).countryComponent.inject(this)
        binding = FragmentDataCountryBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(activity as CountryActivity).get(SharedViewModel::class.java)
        binding.viewModel = viewModel

//        viewModel.countryList.observe(viewLifecycleOwner, Observer {
//            val capital = it?.continent()?.countries()?.get(10)?.capital()
//            binding.textView.text = capital
//        })

        viewModel.country.observe(viewLifecycleOwner, Observer {

        })
        return binding.root
    }


}