package com.marvg.cityweather.data.mapper

import com.marvg.cityweather.data.dto.City
import com.marvg.cityweather.domain.model.CityDisplay

fun City.toCityDisplay(): CityDisplay? {
    return CityDisplay(
        id = id,
        name = name
    )
}