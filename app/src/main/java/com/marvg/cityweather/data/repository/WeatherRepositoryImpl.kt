package com.marvg.cityweather.data.repository

import com.marvg.cityweather.data.WeatherAPI
import com.marvg.cityweather.data.mapper.toCityDisplay
import com.marvg.cityweather.data.mapper.toWeatherDisplay
import com.marvg.cityweather.domain.model.CityDisplay
import com.marvg.cityweather.domain.model.WeatherDisplay
import com.marvg.cityweather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherAPI
): WeatherRepository{

    override suspend fun getCities(): Result<List<CityDisplay>> {
        return try {
            val getCitiesDto = api.getCities()
            Result.success(
                getCitiesDto.cities
                    .mapNotNull { it.toCityDisplay() }
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getCityWeather(cityId: Long): Result<WeatherDisplay> {
        return try {
            val weatherDisplay = api.getCityWeather(cityId)
            Result.success(
                weatherDisplay.toWeatherDisplay()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}