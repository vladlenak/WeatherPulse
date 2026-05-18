package com.vladlenak.weatherpulse.domain.city

data class City(
    val id: Long,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val adminArea: String? = null
)
