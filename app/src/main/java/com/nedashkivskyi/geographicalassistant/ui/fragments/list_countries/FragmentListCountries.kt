package com.nedashkivskyi.geographicalassistant.ui.fragments.list_countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nedashkivskyi.geographicalassistant.databinding.FragmentListCountriesBinding
import com.nedashkivskyi.geographicalassistant.ui.activities.CountryActivity

class FragmentListCountries : Fragment() {
    private lateinit var binding: FragmentListCountriesBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as CountryActivity).countryComponent.inject(this)
        binding = FragmentListCountriesBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        recyclerViewRealization(recyclerView)
        return binding.root
    }

    private fun recyclerViewRealization(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = AdapterRecyclerView()
    }
}