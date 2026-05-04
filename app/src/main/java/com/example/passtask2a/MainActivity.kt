package com.example.passtask2a

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        //Data list
        val locations = listOf(
            Location("City", -38.813, 144.963, "VIC", 1.5F, "25/08/2003", R.drawable.city),
            Location("Fountain", -37.831, 144.984, "NSW", 4.0F, "29/02/2008", R.drawable.localfountain),
            Location("Rural Town", 36.761, 144.282, "VIC", 3.5F, "14/04/2006", R.drawable.ruraltown),
            Location("Suburb", 37.7308, 144.928, "QLD", 5.0F, "06/11/2010", R.drawable.suburbia)
        )

        //Resources Lists
        val images = listOf(
            findViewById<ImageView>(R.id.image1),
            findViewById<ImageView>(R.id.image2),
            findViewById<ImageView>(R.id.image3),
            findViewById<ImageView>(R.id.image4)
        )
        val ratings = listOf(
            findViewById<RatingBar>(R.id.rating1),
            findViewById<RatingBar>(R.id.rating2),
            findViewById<RatingBar>(R.id.rating3),
            findViewById<RatingBar>(R.id.rating4)
        )
        val names = listOf(
            findViewById<TextView>(R.id.name1),
            findViewById<TextView>(R.id.name2),
            findViewById<TextView>(R.id.name3),
            findViewById<TextView>(R.id.name4)
        )

        val defaultColor = names[0].textColors.defaultColor

        //Spinner
        val spinner: Spinner = findViewById(R.id.stateSpinner)
        val states = resources.getStringArray(R.array.states_array)
        ArrayAdapter.createFromResource(
            this,
            R.array.states_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }


        //RadioButton group used to change the color of the text
        val group = findViewById<RadioGroup>(R.id.colorGroup)
        group.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.defaultColorBtn -> names.forEach {it.setTextColor(defaultColor)}
                R.id.redBtn -> names.forEach {it.setTextColor(android.graphics.Color.RED)}
                R.id.greenBtn -> names.forEach {it.setTextColor(android.graphics.Color.GREEN)}
                R.id.blueBtn -> names.forEach { it.setTextColor(android.graphics.Color.BLUE)}
            }
        }

        //Checkbox
        val checkbox = findViewById<CheckBox>(R.id.checkbox)
        val msg = findViewById<TextView>(R.id.msg)

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                msg.visibility = View.VISIBLE
                Log.d("CHECKBOX", "Hidden Information is displayed: ${true}")

            } else {
                msg.visibility = View.GONE
                Log.d("CHECKBOX", "Hidden Information is invisible: ${false}")
            }
        }

        //Filter based on state selected
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedState = states[position]

                val filteredLocations = if (selectedState == "ALL") {
                    locations
                } else {
                    locations.filter { it.state == selectedState }
                }

                //Loop through and display filtered data
                for (i in images.indices) {
                    if (i < filteredLocations.size) {

                        //Set filtered data
                        images[i].setImageResource(filteredLocations[i].imageResId)
                        ratings[i].rating = filteredLocations[i].rating
                        names[i].text = filteredLocations[i].name

                        images[i].visibility = View.VISIBLE
                        ratings[i].visibility = View.VISIBLE
                        names[i].visibility = View.VISIBLE
                        Log.d("DISPLAY", "Filtered Data is displayed")

                        //Listener for when the image is clicked and send data to DetailActivity using intent
                        images[i].setOnClickListener {
                            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                            intent.putExtra("location", filteredLocations[i])
                            startActivity(intent)
                            Log.d("Image Click", "Image has been pressed, Data parsed to DetailActivity")
                        }
                    } else {
                        // Hide unused data
                        images[i].visibility = View.INVISIBLE
                        ratings[i].visibility = View.INVISIBLE
                        names[i].visibility = View.INVISIBLE
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback.
            }
        }
    }
}