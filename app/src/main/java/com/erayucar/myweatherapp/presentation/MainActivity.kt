package com.erayucar.myweatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayucar.myweatherapp.presentation.viewmodel.WeatherViewModel
import com.erayucar.myweatherapp.ui.theme.MyWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.location.LocationManager
import android.Manifest
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erayucar.myweatherapp.presentation.views.WeatherForecastScreen
import com.erayucar.myweatherapp.presentation.views.WeatherScreen
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionlauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionlauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                viewModel.loadWeatherInfo()
            }
        permissionlauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        setContent {
            MyWeatherAppTheme() {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.weatherScreen.route
                ) {
                    composable(Screen.weatherScreen.route,) {

                        WeatherScreen(
                            navController = navController,
                        )
                    }
                    composable(Screen.weatherDeatilScreen.route + "/{city}", arguments = listOf(
                        navArgument("city"){
                            type = NavType.StringType
                        }
                    )) {
                        WeatherForecastScreen()

                    }
                }
            }
        }
    }
}



