package com.example.passtask2a
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location (
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val state: String,
    val rating: Float,
    val date: String,
    val imageResId: Int) : Parcelable