package com.marvg.cityweather.data.dto

import com.squareup.moshi.Json

data class TempDetails (
    @field:Json(name = "temp")
    val currentTemperature: Double?,
    @field:Json(name = "temp_min")
    val minimumTemperature: Double?,
    @field:Json(name = "temp_max")
    val maximumTemperature: Double?,
)