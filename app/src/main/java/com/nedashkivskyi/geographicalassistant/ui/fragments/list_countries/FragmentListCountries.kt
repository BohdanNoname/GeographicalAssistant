package com.nedashkivskyi.geographicalassistant.ui.fragments.list_countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nedashkivskyi.geographicalassistant.databinding.FragmentListCountriesBinding
import com.nedashkivskyi.geographicalassistant.ui.SharedViewModel
import com.nedashkivskyi.geographicalassistant.ui.activities.CountryActivity
import javax.inject.Inject

class FragmentListCountries: Fragment() {

    private lateinit var binding: FragmentListCountriesBinding
    private lateinit var recyclerView: RecyclerView
    @Inject lateinit var viewModel: SharedViewModel
    @Inject lateinit var adapter: AdapterRecyclerView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        (activity as CountryActivity).countryComponent.inject(this)
        binding = FragmentListCountriesBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(activity as CountryActivity)
            .get(SharedViewModel::class.java)

        recyclerView = binding.recyclerView
        recyclerViewRealization(recyclerView)
        return binding.root
    }

    private fun recyclerViewRealization(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter.viewModel = viewModel
        recyclerView.adapter = adapter
    }
}