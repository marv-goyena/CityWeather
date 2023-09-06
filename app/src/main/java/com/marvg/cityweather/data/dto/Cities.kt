package com.marvg.cityweather.data.dto

import com.squareup.moshi.Json

data class Cities (
    @field:Json(name = "list")
    val cities: List<City>
)