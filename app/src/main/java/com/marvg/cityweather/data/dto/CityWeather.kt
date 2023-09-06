package com.marvg.cityweather.data.dto

import com.squareup.moshi.Json

data class CityWeather (
    @field:Json(name = "id")
    val cityId: Long,
    @field:Json(name = "name")
    val cityName: String?,
    @field:Json(name = "coord")
    val coordinates: Coordinates?,
    @field:Json(name = "main")
    val temperature: TempDetails?,
    @field:Json(name = "wind")
    val windDetails: WindDetails?,
    @field:Json(name = "clouds")
    val cloudiness: Clouds?,
    @field:Json(name = "weather")
    val weatherDescription: List<OverallWeather>?
)