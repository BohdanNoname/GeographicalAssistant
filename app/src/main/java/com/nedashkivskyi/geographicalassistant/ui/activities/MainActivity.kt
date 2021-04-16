package com.nedashkivskyi.geographicalassistant.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.nedashkivskyi.geographicalassistant.App
import com.nedashkivskyi.geographicalassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as App).appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

//    OnClick method in the XML attribute in the layout
    fun startCountryActivity(view: View){
        val continentName = view.findViewById<TextView>(view.id).text

        val intent = Intent(this, CountryActivity::class.java)
        intent.putExtra(App.ACTIVITY_INTENT_TAG, continentName)
        startActivity(intent)
    }
}