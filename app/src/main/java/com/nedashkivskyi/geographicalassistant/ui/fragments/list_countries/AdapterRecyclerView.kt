package com.nedashkivskyi.geographicalassistant.ui.fragments.list_countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nedashkivskyi.geographicalassistant.databinding.RecyclerViewLineBinding
import com.nedashkivskyi.geographicalassistant.models.Country

class AdapterRecyclerView: RecyclerView.Adapter<AdapterRecyclerView.CountryViewHolder>() {
    private val countyList = emptyList<Country>()

    class CountryViewHolder(private val binding: RecyclerViewLineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country){
            binding.country = country
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): CountryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerViewLineBinding.inflate(layoutInflater, parent, false)
                return CountryViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countyList[position])
    }

    override fun getItemCount(): Int {
        return countyList.size
    }
}