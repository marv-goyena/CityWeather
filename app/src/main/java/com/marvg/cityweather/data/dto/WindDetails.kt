package com.marvg.cityweather.data.dto

import com.squareup.moshi.Json

data class WindDetails (
    val speed: Double?,
    @field:Json(name = "deg")
    val degrees: Double?
)