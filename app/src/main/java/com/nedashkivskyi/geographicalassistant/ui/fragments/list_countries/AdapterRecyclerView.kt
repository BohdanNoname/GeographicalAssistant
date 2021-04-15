package com.nedashkivskyi.geographicalassistant.ui.fragments.list_countries

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.nedashkivskyi.geographicalassistant.CountryQuery
import com.nedashkivskyi.geographicalassistant.R
import com.nedashkivskyi.geographicalassistant.databinding.RecyclerViewLineBinding
import com.nedashkivskyi.geographicalassistant.ui.SharedViewModel
import com.nedashkivskyi.geographicalassistant.ui.fragments.data_countries.FragmentDataCountry
import okio.IOException
import javax.inject.Inject

class AdapterRecyclerView @Inject constructor(): RecyclerView.Adapter<AdapterRecyclerView.CountryViewHolder>() {
    @Inject lateinit var viewModel: SharedViewModel
    @Inject lateinit var apolloClient: ApolloClient

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(viewModel, apolloClient, position)
    }

    override fun getItemCount(): Int {
        return viewModel.countryList.value?.continent()?.countries()?.size!!
    }

    class CountryViewHolder(private val binding: RecyclerViewLineBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(viewModel: SharedViewModel
                 , apolloClient: ApolloClient
                 , position: Int) {

            val country = viewModel.countryList.value
                    ?.continent()?.countries()?.get(position)!!

            binding.countryName.text = country.name()

            if (country.capital() != null) {
                binding.countryCapital.text = country.code() + " - " + country.capital()
            } else { binding.countryCapital.text = country.code() }

            val code = country.code()

            binding.itemView.setOnClickListener{
                apolloClient.query(CountryQuery(code)).enqueue(
                        object: ApolloCall.Callback<CountryQuery.Data>(){
                            override fun onResponse(response: Response<CountryQuery.Data>) {
                                try {
                                    viewModel.setCountryResponse(response.data)
                                    replaceFragmentCountryData(it)
                                } catch (e: IOException){
                                    Log.d("IOException", e.message.toString())
                                }
                            }
                            override fun onFailure(e: ApolloException) {
                                Log.d("onFailure", e.message.toString())
                            }
                        })
                binding.executePendingBindings()
            }
        }

        companion object{
            fun from(parent: ViewGroup): CountryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerViewLineBinding
                    .inflate(layoutInflater, parent, false)
                return CountryViewHolder(
                        binding
                )
            }
        }

        private fun replaceFragmentCountryData(it: View){
            val application = it.context as AppCompatActivity

            application.supportFragmentManager.commit {
                replace<FragmentDataCountry>(R.id.country_data)
                setReorderingAllowed(true)
                addToBackStack("replace_data_fragment")
            }
        }
    }
}
