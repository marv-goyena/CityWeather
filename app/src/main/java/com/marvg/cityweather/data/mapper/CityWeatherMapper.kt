package com.marvg.cityweather.data.mapper

import com.marvg.cityweather.data.dto.CityWeather
import com.marvg.cityweather.domain.model.WeatherDisplay

fun CityWeather.toWeatherDisplay(): WeatherDisplay {
    return WeatherDisplay(
        cityId = cityId,
        cityName = cityName,
        latitude = coordinates?.lat,
        longitude = coordinates?.lon,
        currentTemperature = temperature?.currentTemperature,
        minimumTemperature = temperature?.minimumTemperature,
        maximumTemperature = temperature?.maximumTemperature,
        cloudiness = cloudiness?.cloudiness,
        weatherDescription = weatherDescription?.first()?.description,
        windSpeed = windDetails?.speed,
        windDegrees = windDetails?.degrees
    )
}