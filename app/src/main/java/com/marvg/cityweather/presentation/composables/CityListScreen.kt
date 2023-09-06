package com.marvg.cityweather.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.marvg.cityweather.presentation.CityWeatherViewModel
import com.marvg.cityweather.presentation.Event
import com.marvg.cityweather.presentation.ui.theme.LocalSpacing
import com.marvg.cityweather.presentation.util.UiEvent

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun CityListScreen(
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit,
    onCityClick: (Long) -> Unit = {},
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
        Button(
            onClick = { viewModel.onEvent(Event.OnSearchCities) },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = true,
            shape = RoundedCornerShape(100.dp)
        ) {
            Text(
                text = stringResource(id = com.marvg.cityweather.R.string.display_cities),
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
            )
        }

        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                items = state.cities,
            ) { city ->
                city.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = false,
                                onClick = {
                                    //viewModel.onEvent(Event.OnSearchCityWeather(city.id))
                                    onCityClick (city.id)
                                }
                            )
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
        }
    }

}