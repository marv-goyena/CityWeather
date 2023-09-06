package com.marvg.cityweather.domain.usecase

import com.marvg.cityweather.domain.model.WeatherDisplay
import com.marvg.cityweather.domain.repository.WeatherRepository

class GetCityWeather(
    private val repository: WeatherRepository
)
{
    suspend operator fun invoke(cityId: Long): Result<WeatherDisplay> {
        return repository.getCityWeather(cityId)
    }
}
