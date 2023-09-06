package com.marvg.cityweather.presentation

sealed class Event {

    object OnSearchCities : Event()

    data class OnSearchCityWeather(
        val cityId: Long
    ) : Event()
}