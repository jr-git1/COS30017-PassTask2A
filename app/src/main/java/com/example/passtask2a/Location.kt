package com.example.passtask2a
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location (
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Float,
    val imageResId: Int) : Parcelable