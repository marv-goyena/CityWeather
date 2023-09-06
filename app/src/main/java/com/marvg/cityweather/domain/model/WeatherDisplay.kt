package com.marvg.cityweather.domain.model

data class WeatherDisplay(
    val cityId: Long?,
    val cityName: String?,
    val latitude: Double?,
    val longitude: Double?,
    val currentTemperature: Double?,
    val minimumTemperature: Double?,
    val maximumTemperature: Double?,
    val windSpeed: Double?,
    val windDegrees: Double?,
    val cloudiness: Int?,
    val weatherDescription: String?
)
