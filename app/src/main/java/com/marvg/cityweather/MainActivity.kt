package com.marvg.cityweather

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.marvg.cityweather.presentation.composables.CityDetailsScreen
import com.marvg.cityweather.presentation.composables.CityListScreen
import com.marvg.cityweather.presentation.navigation.Route
import com.marvg.cityweather.presentation.ui.theme.CityWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CityWeatherTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.CITYLIST
                    ) {
                        composable(Route.CITYLIST) {
                            CityListScreen(
                                scaffoldState = scaffoldState,
                                onNavigateUp = {
                                    navController.navigateUp()
                                },
                                onCityClick = { cityId ->
                                    navController.navigate(
                                        Route.CITYWEATHER + "/$cityId"
                                    )
                                }
                            )
                        }
                        composable(
                            route = Route.CITYWEATHER + "/{cityId}",
                            arguments = listOf(
                                navArgument("cityId") {
                                    type = NavType.LongType
                                }
                            )
                        ) {
                            val cityId = it.arguments?.getLong("cityId")!!
                            CityDetailsScreen(
                                scaffoldState = scaffoldState,
                                cityId = cityId,
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
