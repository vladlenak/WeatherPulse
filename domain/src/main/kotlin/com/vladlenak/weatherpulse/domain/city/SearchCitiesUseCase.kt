package com.vladlenak.weatherpulse.domain.city

class SearchCitiesUseCase(
    private val cityRepository: CityRepository
) {
    suspend operator fun invoke(query: String): List<City> {
        val normalizedQuery = query.trim()

        if (normalizedQuery.isEmpty()) {
            return emptyList()
        }

        return cityRepository.searchCities(normalizedQuery)
    }
}
