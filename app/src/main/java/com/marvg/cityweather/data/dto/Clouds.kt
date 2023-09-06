package com.marvg.cityweather.data.dto

import com.squareup.moshi.Json

data class Clouds (
    @field:Json(name = "all")
    val cloudiness: Int?
)