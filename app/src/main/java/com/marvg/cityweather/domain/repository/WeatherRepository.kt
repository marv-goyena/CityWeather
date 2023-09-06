package com.marvg.cityweather.domain.repository

import com.marvg.cityweather.domain.model.CityDisplay
import com.marvg.cityweather.domain.model.WeatherDisplay

interface WeatherRepository {

    suspend fun getCities() : Result<List<CityDisplay>>
    suspend fun getCityWeather(cityId: Long) : Result<WeatherDisplay>
}
