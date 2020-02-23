package com.albertattard.example.micronaut.weather

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View

@Controller("/weather")
class WeatherController internal constructor(
    private var service: WeatherService
) {

    @Get("/")
    @View("index")
    fun index(): HttpResponse<Forecast> {
        return HttpResponse.ok(service.latestForecast())
    }

    @Get("/forecast", produces = [MediaType.APPLICATION_JSON])
    fun forecast(): Forecast {
        return service.latestForecast()
    }
}
