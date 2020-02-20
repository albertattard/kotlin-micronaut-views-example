package com.albertattard.example.micronaut.weather

import io.micronaut.runtime.context.scope.Refreshable
import java.text.SimpleDateFormat
import java.util.Date
import javax.annotation.PostConstruct
import org.slf4j.LoggerFactory

@Refreshable
class WeatherService {

    private val logger = LoggerFactory.getLogger(WeatherService::class.java)

    private var forecast: Forecast =
        Forecast("Unknown")

    @PostConstruct
    fun init() {
        val caption = "Scattered Clouds ${SimpleDateFormat("dd/MMM/yyyy HH:mm:ss.SSS").format(Date())}"
        logger.debug("Updating weather: $caption")
        forecast = Forecast(caption)
    }

    fun latestForecast(): Forecast {
        return forecast
    }
}
