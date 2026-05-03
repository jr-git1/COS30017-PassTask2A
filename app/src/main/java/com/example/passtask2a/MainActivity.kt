package com.example.passtask2a

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val locations = listOf(
            Location("City", -38.813, 144.963, "VIC", 3.5F, "25/08/2003", R.drawable.city),
            Location("Fountain", -37.831, 144.984, "NSW", 4.0F, "29/02/2008", R.drawable.localfountain),
            Location("Rural Town", 36.761, 144.282, "VIC", 2.0F, "14/04/2006", R.drawable.ruraltown),
            Location("Suburb", 37.7308, 144.928, "QLD", 1.5F, "06/11/2010", R.drawable.suburbia)
        )

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

        for (i in images.indices) {
            images[i].setImageResource(locations[i].imageResId)
            ratings[i].rating = locations[i].rating
            names[i].text = locations[i].name

            images[i].setOnClickListener {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("location", locations[i])
                startActivity(intent)
            }
        }
    }

}