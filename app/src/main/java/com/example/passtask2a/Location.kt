package com.example.passtask2a
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location (
    var name: String,
    var latitude: Double,
    var longitude: Double,
    var state: String,
    var rating: Float,
    var date: String,
    var imageResId: Int) : Parcelable