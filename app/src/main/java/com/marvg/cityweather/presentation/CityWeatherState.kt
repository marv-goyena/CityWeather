package com.marvg.cityweather.presentation

import com.marvg.cityweather.domain.model.CityDisplay
import com.marvg.cityweather.domain.model.WeatherDisplay

data class CityWeatherState (
    val isLoading: Boolean = false,
    val cities: List<CityDisplay> = emptyList(),
    val cityWeather: WeatherDisplay? = null
)