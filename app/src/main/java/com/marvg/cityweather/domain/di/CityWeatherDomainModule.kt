package com.marvg.cityweather.domain.di

import com.marvg.cityweather.domain.repository.WeatherRepository
import com.marvg.cityweather.domain.usecase.CityWeatherUseCases
import com.marvg.cityweather.domain.usecase.GetCities
import com.marvg.cityweather.domain.usecase.GetCityWeather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CityWeatherDomainModule {

    @ViewModelScoped
    @Provides
    fun provideCityWeatherUseCases(
        repository: WeatherRepository,
    ): CityWeatherUseCases {
        return CityWeatherUseCases(
            getCities = GetCities(repository),
            getCityWeather = GetCityWeather(repository)
        )
    }
}