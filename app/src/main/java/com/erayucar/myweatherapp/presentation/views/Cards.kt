package com.erayucar.myweatherapp.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erayucar.myweatherapp.presentation.state.WeatherForecastState


@Composable
fun HumidityCard(
    state: WeatherForecastState,

    modifier: Modifier,
) {
    state.weatherForecast?.let { data ->


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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = "Humidity Rate",
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = "% ${data.current.humidity}",
                    fontSize = 50.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
                Spacer(modifier = Modifier.height(10.dp))


            }
        }
    }
}

@Composable
fun VisibilityCard(
    state: WeatherForecastState,

    modifier: Modifier,
) {
    state.weatherForecast?.let { data ->


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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = "Visibility",
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = " ${data.current.vis_km} km",
                    fontSize = 30.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
                Spacer(modifier = Modifier.height(10.dp))


            }
        }
    }
}

@Composable
fun FeelsLikeCard(
    state: WeatherForecastState,

    modifier: Modifier,
) {
    state.weatherForecast?.let { data ->


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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = "Feels Like",
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = "${data.current.feelslike_c}°C",
                    fontSize = 30.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
                Spacer(modifier = Modifier.height(10.dp))


            }
        }
    }
}
@Composable
fun UvCard(
    state: WeatherForecastState,

    modifier: Modifier,
) {
    state.weatherForecast?.let { data ->


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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = "UV Index",
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight(400),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = "${data.current.uv}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
                Spacer(modifier = Modifier.height(10.dp))


            }
        }
    }
}