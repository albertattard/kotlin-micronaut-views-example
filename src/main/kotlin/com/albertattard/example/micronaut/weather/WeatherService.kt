package com.albertattard.example.micronaut.weather

interface WeatherService {
    fun latestForecast(): Forecast
}
