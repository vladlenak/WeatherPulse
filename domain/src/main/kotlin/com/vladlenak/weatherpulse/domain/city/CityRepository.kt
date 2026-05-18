package com.vladlenak.weatherpulse.domain.city

interface CityRepository {
    suspend fun searchCities(query: String): List<City>
}
