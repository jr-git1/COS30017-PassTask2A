package com.example.passtask2a

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.ComponentActivity

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)

        val location = intent.getParcelableExtra<Location>("location")
        if (location == null) {
            finish()
            return
        }

        location.let {
            val vImage = findViewById<ImageView>(R.id.image1)
            vImage.setImageResource(it.imageResId)

            val vName = findViewById<TextView>(R.id.name)
            vName.text = it.name

            val vRating = findViewById<RatingBar>(R.id.rating)
            vRating.rating = it.rating

            val vLat = findViewById<TextView>(R.id.latitude)
            vLat.text = it.latitude.toString()

            val vLon = findViewById<TextView>(R.id.longitude)
            vLon.text = it.longitude.toString()
        }
    }
}