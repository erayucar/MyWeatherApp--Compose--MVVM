package com.erayucar.myweatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.erayucar.myweatherapp.presentation.viewmodel.WeatherViewModel
import com.erayucar.myweatherapp.ui.theme.MyWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erayucar.myweatherapp.presentation.views.WeatherForecastScreen
import com.erayucar.myweatherapp.presentation.views.WeatherScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MyWeatherAppTheme() {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.weatherScreen.route
                ) {
                    composable(Screen.weatherScreen.route) {

                        WeatherScreen(
                            navController = navController,
                        )
                    }
                    composable(Screen.weatherDeatilScreen.route + "/{city}", arguments = listOf(
                        navArgument("city") {
                            type = NavType.StringType
                        }
                    )) {
                        WeatherForecastScreen()

                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        println("onResume")


    }

}




