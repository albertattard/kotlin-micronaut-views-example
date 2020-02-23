package com.albertattard.example.micronaut.weather

import java.util.Random
import javax.inject.Singleton

@Singleton
class RandomWeatherService : WeatherService {

    /* A set of weather forecasts from which one will be picked at random */
    private val forecastCaptions = arrayOf("Sunny", "Scattered Clouds", "Light Rain", "Thunderstorm")

    /* Used to pick a forecast at random */
    private val random = Random()

    override fun latestForecast(): Forecast {
        val caption = forecastCaptions[random.nextInt(forecastCaptions.size)]
        return Forecast(caption)
    }
}
