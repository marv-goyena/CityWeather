package com.marvg.cityweather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvg.cityweather.R
import com.marvg.cityweather.domain.usecase.CityWeatherUseCases
import com.marvg.cityweather.presentation.util.UiEvent
import com.marvg.cityweather.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel @Inject constructor(
    private val cityWeatherUseCases: CityWeatherUseCases
): ViewModel() {

    var state by mutableStateOf(CityWeatherState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: Event) {
        when(event) {
            is Event.OnSearchCities -> { executeSearchCities() }
            is Event.OnSearchCityWeather -> {
                executeSearchCityWeather(event.cityId)
            }
        }
    }

    private fun executeSearchCityWeather(cityId: Long) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                cityWeather = null
            )
            cityWeatherUseCases
                .getCityWeather(cityId)
                .onSuccess { cityWeather ->
                    state = state.copy(
                        cityWeather = cityWeather,
                        isLoading = false
                    )
                    _uiEvent.send(UiEvent.Success)
                }
                .onFailure {
                    state = state.copy(isLoading = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }
        }

    }

    private fun executeSearchCities() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                cities = emptyList()
            )
            cityWeatherUseCases
                .getCities()
                .onSuccess { cities ->
                    state = state.copy(
                        cities = cities,
                        isLoading = false
                    )
                }
                .onFailure {
                    state = state.copy(isLoading = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }
        }
    }
}