package com.erayucar.myweatherapp.presentation.views

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.erayucar.myweatherapp.R
import com.erayucar.myweatherapp.data.remote.dto.Hour
import com.erayucar.myweatherapp.data.remote.dto.toFormatDateTime
import com.erayucar.myweatherapp.domain.model.WeatherForecastInfo
import com.erayucar.myweatherapp.domain.model.toFormatUrl
import com.erayucar.myweatherapp.presentation.Screen
import com.erayucar.myweatherapp.presentation.state.WeatherForecastState
import com.erayucar.myweatherapp.presentation.state.WeatherState
import com.erayucar.myweatherapp.presentation.viewmodel.WeatherForecastViewModel
import com.erayucar.myweatherapp.ui.theme.CardColor
import com.erayucar.myweatherapp.ui.theme.MyWeatherAppTheme
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle

@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastViewModel = hiltViewModel(),

    ) {
    val state = viewModel.weatherState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF38A5D6))
            .padding(20.dp),


        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        if (state.isLoading) {
            CircularProgressIndicator(color = Color(0xFFFFFFFF))
        } else if (state.error.isNotEmpty()) {
            Text(text = state.error)
        } else {
            // WeatherCard(state = state)
            state.weatherForecast?.let {
                WeatherForacastCard(
                    state = state,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Today's Hourly Weather Forecast",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(it.dayHour) {
                        HourlyWeatherDisplay(
                            weatherData = it, modifier = Modifier
                                .height(200.dp)
                        )
                    }

                }

            }

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherForacastCard(
    state: WeatherForecastState,
    backgroundColor: Color = Color(0xFF21234E),

    modifier: Modifier,
) {
    state.weatherForecast?.let { data ->
        Text(
            text = data.location,
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFFFFFFFF),
        )
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
                .padding(15.dp),
            colors = CardDefaults.cardColors(Color(0x4DFFFFFF)),


            ) {
            Column(

                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = "Today ${data.currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(25.dp))
                LoadImage(path = data.toFormatUrl(data.conditionImageUrl))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = data.condition, color = Color(0xFFFFFFFF), fontSize = 16.sp)
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${data.temperature}°C",
                    fontSize = 50.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )

            }
        }
    }
}


@Composable
fun HourlyWeatherDisplay(
    weatherData: Hour,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = weatherData.toFormatDateTime(weatherData.time)
                .format(DateTimeFormatter.ofPattern("HH:mm")),
            color = textColor
        )
        LoadImage(path = "https:" + weatherData.condition.icon)
        Text(
            text = "${weatherData.temp_c}°C",
            color = textColor,
            fontWeight = FontWeight.Bold,
        )
    }
}

