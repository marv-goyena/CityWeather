package com.marvg.cityweather.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.marvg.cityweather.R
import com.marvg.cityweather.presentation.CityWeatherViewModel
import com.marvg.cityweather.presentation.Event
import com.marvg.cityweather.presentation.ui.theme.LocalSpacing
import com.marvg.cityweather.presentation.util.UiEvent

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun CityDetailsScreen (
    scaffoldState: ScaffoldState,
    cityId: Long,
    onNavigateUp: () -> Unit,
    viewModel: CityWeatherViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is UiEvent.NavigateUp -> onNavigateUp()

                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ) {
        state?.cityWeather?.cityName?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h2
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceLarge))
        state?.cityWeather?.weatherDescription?.let {
            Text(
                text = context.getString(R.string.weather_today_message, it),
                style = MaterialTheme.typography.h4
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        state?.cityWeather?.currentTemperature?.let {
            Text(
                text = context.getString(R.string.current_temp_message, it),
                style = MaterialTheme.typography.h4
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        state?.cityWeather?.minimumTemperature?.let {
            Text(
                text = context.getString(R.string.minimum_temp_message, it),
                style = MaterialTheme.typography.h4
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        state?.cityWeather?.maximumTemperature?.let {
            Text(
                text = context.getString(R.string.maximum_temp_message, it),
                style = MaterialTheme.typography.h4
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        state?.cityWeather?.windSpeed?.let {
            Text(
                text = context.getString(R.string.wind_speed_message, it),
                style = MaterialTheme.typography.h4
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        state?.cityWeather?.windDegrees?.let {
            Text(
                text = context.getString(R.string.wind_direction_message, it),
                style = MaterialTheme.typography.h4
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        state?.cityWeather?.cloudiness?.let {
            Text(
                text = context.getString(R.string.cloudiness_message, it),
                style = MaterialTheme.typography.h4
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.cityWeather == null -> viewModel.onEvent(Event.OnSearchCityWeather(cityId))
        }
    }

}