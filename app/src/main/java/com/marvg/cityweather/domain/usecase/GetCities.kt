package com.marvg.cityweather.domain.usecase

import com.marvg.cityweather.domain.model.CityDisplay
import com.marvg.cityweather.domain.repository.WeatherRepository

class GetCities (
    private val repository: WeatherRepository
    )
{
    suspend operator fun invoke(): Result<List<CityDisplay>> {
        return repository.getCities()
    }
}