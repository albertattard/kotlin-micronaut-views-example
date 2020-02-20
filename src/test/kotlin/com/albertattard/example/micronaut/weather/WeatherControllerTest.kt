package com.albertattard.example.micronaut.weather

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.kotlintest.MicronautKotlinTestExtension.getMock
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

@MicronautTest
class WeatherControllerTest(
    private val service: WeatherService,
    @Client("/weather") private val client: RxHttpClient
) : StringSpec({
    "should return the weather returned by the weather service" {
        val mock = getMock(service)

        val forecast =
            Forecast("Sunny Mirconaut Framework")
        every { mock.latestForecast() } returns forecast

        val result = client.toBlocking().retrieve("/forecast", Forecast::class.java)
        result shouldBe forecast

        verify(exactly = 1) { mock.latestForecast() }

        verify(exactly = 1) { mock.toString() }
        confirmVerified(mock)
    }
}) {
    @MockBean(WeatherService::class)
    fun weatherService(): WeatherService {
        return mockk()
    }
}
