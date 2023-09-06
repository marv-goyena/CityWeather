package com.marvg.cityweather.data

import com.marvg.cityweather.data.dto.Cities
import com.marvg.cityweather.data.dto.CityWeather
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAPI {

    @GET("weather/list")
    suspend fun getCities(): Cities

    @GET("weather/{id}")
    suspend fun getCityWeather(@Path("id") cityId: Long): CityWeather

    companion object {
        const val BASE_URL = "https://testapi.io/api/olestang/"
    }
}