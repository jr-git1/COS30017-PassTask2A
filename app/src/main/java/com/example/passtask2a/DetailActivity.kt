package com.example.passtask2a

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import androidx.activity.ComponentActivity
import androidx.activity.addCallback

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)

        val location = intent.getParcelableExtra<Location>("location")

        if (location == null) {
            finish()
            return
        }

        val vImage = findViewById<ImageView>(R.id.image1)
        val vEditName = findViewById<EditText>(R.id.name)
        val vEditState = findViewById<EditText>(R.id.state)
        val vEditLat = findViewById<EditText>(R.id.latitude)
        val vEditLon = findViewById<EditText>(R.id.longitude)
        val vEditDate = findViewById<EditText>(R.id.date)
        val vRating = findViewById<RatingBar>(R.id.rating)

        vImage.setImageResource(location.imageResId)
        vEditName.setText(location.name)
        vEditState.setText(location.state)
        vEditLat.setText(location.latitude.toString())
        vEditLon.setText(location.longitude.toString())
        vEditDate.setText(location.date)
        vRating.rating = location.rating

        onBackPressedDispatcher.addCallback(this) {
            location.name = vEditName.text.toString()
            location.state = vEditState.text.toString()
            location.latitude = vEditLat.text.toString().toDouble()
            location.longitude = vEditLon.text.toString().toDouble()
            location.date = vEditDate.text.toString()
            location.rating = vRating.rating

            val resultIntent = Intent()

            resultIntent.putExtra("location", location)

            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }
}