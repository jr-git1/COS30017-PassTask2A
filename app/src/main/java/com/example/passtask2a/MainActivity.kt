package com.example.passtask2a

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val locations = listOf(
            Location("City", -38.813, 144.963, 3.5F, R.drawable.city),
            Location("Fountain", -37.831, 144.984, 4.0F, R.drawable.localfountain),
            Location("Rural Town", 36.761, 144.282, 2.0F, R.drawable.ruraltown),
            Location("Suburb", 37.7308, 144.928, 1.5F, R.drawable.suburbia)
        )

        val images = listOf(
            findViewById<ImageView>(R.id.image1),
            findViewById<ImageView>(R.id.image2),
            findViewById<ImageView>(R.id.image3),
            findViewById<ImageView>(R.id.image4)
        )

        for (i in images.indices) {
            images[i].setImageResource(locations[i].imageResId)

            images[i].setOnClickListener {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("location", locations[i])
                startActivity(intent)
            }
        }
    }
}