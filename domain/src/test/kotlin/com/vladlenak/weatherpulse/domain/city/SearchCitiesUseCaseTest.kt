package com.vladlenak.weatherpulse.domain.city

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SearchCitiesUseCaseTest {

    private val repository = FakeCityRepository()
    private val useCase = SearchCitiesUseCase(repository)

    @Test
    fun `returns empty list and skips repository when query is blank`() = runTest {
        val result = useCase("   ")

        assertTrue(result.isEmpty())
        assertEquals(0, repository.searchCallCount)
    }

    @Test
    fun `trims query before searching cities`() = runTest {
        useCase("  Berlin  ")

        assertEquals(listOf("Berlin"), repository.receivedQueries)
    }

    @Test
    fun `returns cities from repository for valid query`() = runTest {
        val berlin = City(
            id = 2_950_159,
            name = "Berlin",
            country = "Germany",
            latitude = 52.52,
            longitude = 13.41,
            adminArea = "Berlin"
        )
        repository.citiesToReturn = listOf(berlin)

        val result = useCase("Berlin")

        assertEquals(listOf(berlin), result)
    }

    private class FakeCityRepository : CityRepository {
        var searchCallCount = 0
            private set

        val receivedQueries = mutableListOf<String>()
        var citiesToReturn = emptyList<City>()

        override suspend fun searchCities(query: String): List<City> {
            searchCallCount += 1
            receivedQueries += query
            return citiesToReturn
        }
    }
}
